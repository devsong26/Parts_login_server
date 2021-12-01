package parts.login.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import parts.login.service.AuthorityService;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Setter
@Getter
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Collection<? extends GrantedAuthority> authorities;

    private String token;

    private CustomUserDetails(){}

    private CustomUserDetails(User user){
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUsername(user.getUsername());
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.setAuthorities(builder.fromAuthority(user));
        customUserDetails.setEnabled(true);
        customUserDetails.setAccountNonExpired(true);
        customUserDetails.setAccountNonLocked(true);
        customUserDetails.setCredentialsNonExpired(true);
    }

    public static CustomUserDetails.Builder builder = new CustomUserDetails.Builder();

    public static class Builder {

        @Autowired
        private AuthorityService authorityService;

        public CustomUserDetails build(User user){
            return new CustomUserDetails(user);
        }

        public Collection<? extends GrantedAuthority> fromAuthority(User user){
            List<Authority> authList =
                    authorityService.findByUsername(user.getUsername());

            return authList.stream()
                    .map(auth -> new SimpleGrantedAuthority(auth.getAuthority()))
                    .collect(Collectors.toList());
        }

        public void fromToken(CustomUserDetails details){
            UUID uuid = UUID.nameUUIDFromBytes(details.getUsername().getBytes(StandardCharsets.UTF_8));
            details.setToken(uuid.toString());
        }

    }

}
