package parts.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import parts.login.domain.CustomUserDetails;
import parts.login.domain.User;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException(String.format("Not found username >> %s", username));
        }

        return buildCredentials(user);
    }

    public UserDetails buildCredentials(User user){
        return CustomUserDetails.newBuilder()
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setAuthorities(
                        authorityService.findByUsername(user.getUsername())
                                .stream()
                                .map(auth -> new SimpleGrantedAuthority(auth.getAuthority()))
                                .collect(Collectors.toList())
                )
                .setEnabled(true)
                .setAccountNonExpired(true)
                .setAccountNonLocked(true)
                .setCredentialsNonExpired(true)
                .setToken(user.getUsername())
                .build();
    }

}
