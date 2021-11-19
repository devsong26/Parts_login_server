package parts.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import parts.login.domain.Authority;

import java.util.List;

@Mapper
public interface AuthorityMapper {

    List<Authority> findByUsername(String username);

}
