package parts.login.service;

import org.springframework.stereotype.Service;
import parts.login.domain.User;
import parts.login.mapper.UserMapper;

@Service
public class UserService {

    private UserMapper userMapper;

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

}
