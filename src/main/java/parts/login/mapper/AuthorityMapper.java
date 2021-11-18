package parts.login.mapper;

import parts.login.domain.Authority;

import java.util.List;

public interface AuthorityMapper {

    List<Authority> findByUsername(String username);

}
