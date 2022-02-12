package parts.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import parts.login.domain.LoginHistory;
import parts.login.mapper.LoginHistoryMapper;

import static parts.login.config.AppConfig.THREAD_EXECUTOR_NAME;

@Service
public class LoginHistoryService {

    @Autowired
    private LoginHistoryMapper mapper;

    @Autowired
    @Qualifier(THREAD_EXECUTOR_NAME)
    private ThreadPoolTaskExecutor customThreadExecutor;

    public void save(LoginHistory loginHistory) {
        customThreadExecutor.submit(() -> mapper.save(loginHistory));
    }

}
