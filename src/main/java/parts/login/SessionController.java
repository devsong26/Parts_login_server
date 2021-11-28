package parts.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import parts.login.service.RedisService;

import java.util.Optional;

@RestController
public class SessionController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/session/info")
    public String getSessionInfo(String key){
        String value = Optional.of(redisService.getValue(key)).orElse("None");
        return value;
    }

}
