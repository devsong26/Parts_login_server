package parts.login.domain;

import org.springframework.http.HttpStatus;

public interface Response<T> {

    T getData();

    String getMessage();

    HttpStatus getStatus();

}
