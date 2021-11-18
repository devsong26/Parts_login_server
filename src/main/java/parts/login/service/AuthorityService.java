package parts.login.service;

import org.springframework.stereotype.Service;
import parts.login.domain.Authority;
import parts.login.mapper.AuthorityMapper;

import java.util.List;

@Service
public class AuthorityService {

    private AuthorityMapper authorityMapper;

    public List<Authority> findByUsername(String username){
        return authorityMapper.findByUsername(username);
    }

}
