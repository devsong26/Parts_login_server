package parts.login.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import parts.login.domain.LoginHistory;

@SpringBootTest
public class LoginHistoryServiceTest {

    @Autowired
    private LoginHistoryService loginHistoryService;

    @Test
    public void test_save(){
        //given
        LoginHistory loginHistory =
                LoginHistory.builder()
                        .userId(1)
                        .userAgent("ChromeBrowser")
                        .build();

        //when
        loginHistoryService.save(loginHistory);
    }

}