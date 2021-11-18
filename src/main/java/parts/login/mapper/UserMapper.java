package parts.login.mapper;

import parts.login.domain.User;

public interface UserMapper {

    User findByUsername(String username);

}
