package parts.login.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class User {

    private Integer id;
    private String username;
    private String password;

}
