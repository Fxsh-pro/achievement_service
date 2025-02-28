package faang.school.achievement.config;

import faang.school.achievement.config.threadpool.WriterAchievementThreadPoolConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@RequiredArgsConstructor
public class AsyncConfig {
    private final EventHandlerThreadPoolConfig threadPoolConfig;
    private final WriterAchievementThreadPoolConfig writerAchievementThreadPoolConfig;

    @Bean
    public Executor eventHandlerExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setQueueCapacity(threadPoolConfig.getQueueCapacity());
        executor.setMaxPoolSize(threadPoolConfig.getMaxPoolSize());
        executor.setCorePoolSize(threadPoolConfig.getCorePoolSize());
        executor.setThreadNamePrefix(threadPoolConfig.getThreadNamePrefix());
        executor.initialize();
        return executor;
    }

    @Bean
    public Executor writerAchievementExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setQueueCapacity(writerAchievementThreadPoolConfig.getQueueCapacity());
        executor.setMaxPoolSize(writerAchievementThreadPoolConfig.getMaxPoolSize());
        executor.setCorePoolSize(writerAchievementThreadPoolConfig.getCorePoolSize());
        executor.initialize();

        return executor;
    }
}