package parts.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import parts.login.client.RedisClient;

import java.util.Optional;

@RestController
public class SessionController {

    @Autowired
    private RedisClient redisClient;

    @GetMapping("/session/info")
    public String getSessionInfo(String key){
        return Optional.of(redisClient.getValue(key)).orElse("None");
    }

}
