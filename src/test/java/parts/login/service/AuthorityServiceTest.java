package parts.login.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import parts.login.domain.Authority;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class AuthorityServiceTest {

    @Autowired
    private AuthorityService authorityService;

    @Test
    public void test_findByUsername(){
        String username = "user";
        List<Authority> authorities = authorityService.findByUsername(username);

        assertEquals(authorities.size() , 1);
        assertEquals(authorities.get(0).getAuthority(), "USER");
        assertEquals(authorities.get(0).getUsername(), "user");
    }

}
