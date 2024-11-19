package com.smart.booking.batch;

import com.smart.booking.domain.payment.entity.PaymentPartnerShare;
import com.smart.booking.domain.payment.service.PaymentService;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
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

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info(">>>>> This is Step1");

        OffsetDateTime fromDateTime = OffsetDateTime.from(LocalDateTime.now().minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0));
        var toDateTime = LocalDateTime.now().minusDays(1).withHour(23).withMinute(59).withSecond(59).withNano(999999);
        var payments = paymentService.getPaymentsByDateTime(fromDateTime, OffsetDateTime.from(toDateTime));
        return RepeatStatus.FINISHED;
    }
}
