package parts.login.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class LoginHistory {

    private int userId;
    private String userAgent;
    private LocalDateTime logDate;

}
