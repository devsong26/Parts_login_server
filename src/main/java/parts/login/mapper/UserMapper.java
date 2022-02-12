package parts.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import parts.login.domain.User;

@Mapper
public interface UserMapper {

    User findByUsername(String username);

    void save(User user);

}
