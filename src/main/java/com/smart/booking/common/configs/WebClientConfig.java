package com.smart.booking.common.configs;

import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.resolver.DefaultAddressResolverGroup;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@FunctionalInterface
interface ThrowingConsumer<T, E extends Throwable> {

    static <T, E extends Throwable> Consumer<T> unchecked(ThrowingConsumer<T, E> f) {
        return t -> {
            try {
                f.accept(t);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }

    void accept(T t) throws E;
}

@Slf4j
@Component
public class WebClientConfig {

    private static final int READ_TIMEOUT_MILLIS = 10000;
    private static final int WRITE_TIMEOUT_MILLIS = 10000;
    private static final int MAX_IN_MEMORY_SIZE = 1024 * 1024 * 50;


    @Bean
    public WebClient webClient() {
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(MAX_IN_MEMORY_SIZE))
            .build();

        exchangeStrategies.messageWriters().stream()
            .filter(LoggingCodecSupport.class::isInstance)
            .forEach(writer -> ((LoggingCodecSupport) writer).setEnableLoggingRequestDetails(true));

        /**
         maxConnections : 유지할 Connection Pool의 수
         maxIdleTime : 사용하지 않는 상태(idle)의 Connection이 유지되는 시간 (ALB Idle timeout (default:60초) 보다 작게 설정해야 함..)
         maxLifeTime : Connection Pool 에서의 최대 수명 시간
         pendingAcquireTimeout : Connection pool 이 모두 가용하지 않을때 얻기 위해 대기하는 시간
         pendingAcquireMaxCount : Connection을 얻기 위해 대기하는 최대 수
         evictInBackground : 백그라운드에서 만료된 connection을 제거하는 주기
         lifo : 마지막에 사용된 커넥션을 재 사용
         metrics : connection pool 사용 정보를 actuator metric에 노출
         */
        ConnectionProvider provider = ConnectionProvider.builder("service-backend-provider")
            .maxConnections(100)
            .maxIdleTime(Duration.ofSeconds(58))
            .maxLifeTime(Duration.ofSeconds(58))
            .pendingAcquireTimeout(Duration.ofMillis(5000))
            .pendingAcquireMaxCount(-1)
            .evictInBackground(Duration.ofSeconds(30))
            .lifo()
            .metrics(true)
            .build();

        return WebClient.builder()
            .clientConnector(
                new ReactorClientHttpConnector(
                    HttpClient.create(provider)
                        .secure(ThrowingConsumer.unchecked(sslContextSpec -> sslContextSpec.sslContext(
                            SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build()
                        )))
                        .resolver(DefaultAddressResolverGroup.INSTANCE)
                        .responseTimeout(Duration.ofMillis(READ_TIMEOUT_MILLIS))
                        .doOnConnected(conn -> conn
                            .addHandlerLast(new ReadTimeoutHandler(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS))
                        )
                )
            )
            .exchangeStrategies(exchangeStrategies)
            .filter(ExchangeFilterFunction.ofRequestProcessor(Mono::just))
            .filter(ExchangeFilterFunction.ofResponseProcessor(Mono::just))
            .build();
    }
}