package parts.login.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisClient {

    @Autowired
    private RedisTemplate redisTemplate;

    static final long TIME_OUT_MINUTES = 30;

    @SuppressWarnings("unchecked")
    public void setValue(String key, String value){
        redisTemplate.opsForValue().set(key, value, TIME_OUT_MINUTES, TimeUnit.MINUTES);
    }

    public String getValue(String key) throws ClassCastException {
        return (String) redisTemplate.opsForValue().get(key);
    }

}
