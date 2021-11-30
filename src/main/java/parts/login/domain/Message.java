package parts.login.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message {

    private String status;
    private String data;
    private String message;
    private String extra;

}
