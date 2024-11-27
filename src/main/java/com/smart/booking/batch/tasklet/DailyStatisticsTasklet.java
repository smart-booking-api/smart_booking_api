package com.smart.booking.batch.tasklet;

import com.smart.booking.domain.payment.entity.Payment;
import com.smart.booking.domain.payment.entity.PaymentPartnerShare;
import com.smart.booking.domain.payment.service.PaymentService;
import com.smart.booking.domain.settlement.entity.DailySettlement;
import com.smart.booking.domain.settlement.entity.DailySettlement.DailySettlementKey;
import com.smart.booking.domain.settlement.service.DailySettlementService;
import io.lettuce.core.BitFieldArgs.Offset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class DailyStatisticsTasklet implements Tasklet {

    private final PaymentService paymentService;
    private final DailySettlementService dailySettlementService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info(">>>>> This is Step1");

        var fromDateTime = OffsetDateTime.now().minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        var toDateTime = OffsetDateTime.now().minusDays(1).withHour(23).withMinute(59).withSecond(59).withNano(999999);
        var payments = paymentService.getPaymentsByDateTime(fromDateTime, toDateTime);

        var aggregatedSettlements = aggregateSettlements(payments, fromDateTime);
        dailySettlementService.saveAll(aggregatedSettlements);

        return RepeatStatus.FINISHED;
    }

    /**
     * 결제 데이터를 집계하여 DailySettlement 리스트로 변환하는 메서드
     *
     * @param payments     결제 리스트
     * @param fromDateTime 기준 날짜
     *
     * @return 집계된 DailySettlement 리스트
     */
    private List<DailySettlement> aggregateSettlements(List<Payment> payments, OffsetDateTime fromDateTime) {
        // 키별로 총 금액과 개수를 저장하는 Map
        Map<DailySettlementKey, int[]> settlementSums = new HashMap<>();

        // 결제 리스트 반복
        for (var payment : Optional.ofNullable(payments).orElse(Collections.emptyList())) {
            // 각 결제의 파트너 정산 데이터 반복
            for (var partnerShare : Optional.ofNullable(payment.getPartnerShares()).orElse(Collections.emptyList())) {
                // DailySettlementKey 생성 (날짜, 파트너, 티박스 기준으로 그룹화)
                DailySettlementKey key = new DailySettlementKey(
                    fromDateTime.toLocalDate(),
                    partnerShare.getPartner(),
                    payment.getTeeBox()
                );

                // settlementSums 맵에 금액과 개수를 합산 (중복 키는 값을 업데이트)
                settlementSums.merge(key, new int[]{partnerShare.getTotalAmount(), 1},
                    (oldValue, newValue) -> new int[]{
                        oldValue[0] + newValue[0], // 총 금액 합산
                        oldValue[1] + newValue[1]  // 개수 합산
                    });
            }
        }

        // Map 데이터를 DailySettlement 객체 리스트로 변환
        return settlementSums.entrySet().stream()
            .map(entry -> DailySettlement.builder()
                .startDate(entry.getKey().getStartDate()) // 시작 날짜
                .partner(entry.getKey().getPartner())     // 파트너
                .teeBox(entry.getKey().getTeeBox())       // 티박스
                .totalCount(entry.getValue()[1])         // 개수
                .totalAmount(entry.getValue()[0])        // 총 금액
                .build())
            .collect(Collectors.toList());
    }
}
