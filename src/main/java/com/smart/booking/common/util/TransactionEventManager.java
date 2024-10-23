package com.smart.booking.common.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionEventManager {
    private final Executor executor = Executors.newFixedThreadPool(20);

    /**
     * 커밋일 경우만
     */
    public void onTransactionEnd(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (!TransactionSynchronizationManager.isActualTransactionActive()
            || !TransactionSynchronizationManager.isSynchronizationActive()) {
            try {
                runnable.run();
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            return;
        }

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                try {
                    executor.execute(runnable);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        });

    }

    /**
     * 커밋, 롤백 포함
     */
    public void onTransactionCompleted(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (!TransactionSynchronizationManager.isActualTransactionActive()
            || !TransactionSynchronizationManager.isSynchronizationActive()) {
            runnable.run();
            return;
        }

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                TransactionSynchronization.super.afterCompletion(status);
                executor.execute(runnable);
            }
        });
    }

}
