package parts.login.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AppConfig {

    public static final String THREAD_EXECUTOR_NAME = "CustomThreadExecutor";

    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(10);
        return messageSource;
    }

    @Bean(THREAD_EXECUTOR_NAME)
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(
            @Value("${spring.thread.size.core}") int coreSize,
            @Value("${spring.thread.size.max}") int maxSize
    ){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coreSize);
        executor.setMaxPoolSize(maxSize);
        executor.setThreadNamePrefix("myApp - ");
        return executor;
    }

}
