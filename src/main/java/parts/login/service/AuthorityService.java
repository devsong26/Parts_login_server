package parts.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import parts.login.domain.Authority;
import parts.login.mapper.AuthorityMapper;

import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    public List<Authority> findByUsername(String username){
        return authorityMapper.findByUsername(username);
    }

}
