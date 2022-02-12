package parts.login.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import parts.login.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    public void test_save(){
        //given
        final PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = User.builder()
                        .username("user")
                        .password(encoder.encode("123123"))
                        .build();

        //when
        userService.save(user);

        //then
        User result = userService.findByUsername("user");
        assertNotNull(result);
    }

}
