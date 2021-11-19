package parts.login.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import parts.login.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test_findByUsername(){
        String username = "user";
        User user = userService.findByUsername(username);

        assertEquals(username, user.getUsername());
    }

}
