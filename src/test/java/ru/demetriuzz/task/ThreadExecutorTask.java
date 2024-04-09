package ru.demetriuzz.task;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadExecutorTask {

    private static ThreadPoolTaskExecutor executor;

    @BeforeAll
    static void beforeAll() {
        executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("MessageProcessingPool-");
        executor.setMaxPoolSize(4);
        executor.setCorePoolSize(4);
        executor.initialize();
    }

    @AfterAll
    static void afterAll() {
        executor.shutdown();
    }

    @Test
    void waitingTask() {
        Assertions.assertThat(executor.getMaxPoolSize()).isEqualTo(4);
        final var tasks = new ArrayList<Future<Integer>>();

        System.out.println("Запуск заданий");
        // порядок добавления определяет очередность выполнения
        IntStream.rangeClosed(1, 10).forEach(n -> {
            tasks.add(executor.submit(new Task(n)));
            System.out.printf("Задание №%d, запущено%n", n);
            // замедление добавления
            if (5 < n && n < 10) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("ошибка добавления");
                }
            }
        });

        System.out.println("Запуск завершен, ожидание ..");
        tasks.forEach(t -> {
            try {
                System.out.printf(
                        "Задание №%d, завершено | размер пула [%d] | активно заданий [%d]%n",
                        t.get(),
                        executor.getPoolSize(),
                        executor.getActiveCount()
                );
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("ошибка ожидания");
            }
        });

        Assertions.assertThat(executor.getMaxPoolSize()).isEqualTo(4);
        Assertions.assertThat(executor.getPoolSize()).isEqualTo(4);
        Assertions.assertThat(executor.getActiveCount()).isEqualTo(0);
    }

    private record Task(
            Integer number
    ) implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            TimeUnit.SECONDS.sleep(1 + number);
            return number;
        }
    }

}
