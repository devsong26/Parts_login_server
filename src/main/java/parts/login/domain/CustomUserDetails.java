package parts.login.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.UUID;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Collection<? extends GrantedAuthority> authorities;

    private String token;

    public CustomUserDetails(
            String username,
            String password,
            boolean isEnabled,
            boolean isAccountNonExpired,
            boolean isAccountNonLocked,
            boolean isCredentialsNonExpired,
            Collection<? extends GrantedAuthority> authorities,
            String token){
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.authorities = authorities;
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public String getToken(){
        return this.token;
    }

    public static Builder newBuilder(){
        return new Builder();
    }

    public static class Builder extends AbstractBuilder {
        private String username;
        private String password;
        private boolean isEnabled;
        private boolean isAccountNonExpired;
        private boolean isAccountNonLocked;
        private boolean isCredentialsNonExpired;
        private Collection<? extends GrantedAuthority> authorities;

        private String token;

        protected Builder(){}

        public Builder setUsername(String username){
            this.username = username;
            return this;
        }

        public Builder setPassword(String password){
            this.password = password;
            return this;
        }

        public Builder setEnabled(boolean isEnabled){
            this.isEnabled = isEnabled;
            return this;
        }

        public Builder setAccountNonExpired(boolean isAccountNonExpired){
            this.isAccountNonExpired = isAccountNonExpired;
            return this;
        }

        public Builder setAccountNonLocked(boolean isAccountNonLocked){
            this.isAccountNonLocked = isAccountNonLocked;
            return this;
        }

        public Builder setCredentialsNonExpired(boolean isCredentialsNonExpired){
            this.isCredentialsNonExpired = isCredentialsNonExpired;
            return this;
        }

        public Builder setAuthorities(Collection<? extends GrantedAuthority> authorities){
            this.authorities = authorities;
            return this;
        }

        public Builder setToken(String username){
            this.token = UUID.nameUUIDFromBytes(
                    username.getBytes(StandardCharsets.UTF_8))
                    .toString();
            return this;
        }

        @Override
        public CustomUserDetails build() {
            return new CustomUserDetails(
                    this.username
                    , this.password
                    , this.isEnabled
                    , this.isAccountNonExpired
                    , this.isAccountNonLocked
                    , this.isCredentialsNonExpired
                    , this.authorities
                    , this.token
            );
        }

    }

}
