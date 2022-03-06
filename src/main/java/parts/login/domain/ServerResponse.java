package parts.login.domain;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class ServerResponse<T> implements Response<T> {

    private HttpStatus status;
    private T data;
    private String msg;

    private ServerResponse(
            HttpStatus status,
            T data,
            String msg
    ){
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public static <T> Response<T> ok(T data) {
        Objects.requireNonNull(data, "Data must not be null!");
        HttpStatus ok = HttpStatus.OK;
        return new ServerResponse<>(ok, data, ok.getReasonPhrase());
    }

    public static <T> Response<T> error(T data) {
        Objects.requireNonNull(data, "Data must not be null!");
        HttpStatus serverError = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ServerResponse<>(serverError, data, serverError.getReasonPhrase());
    }

    @Override
    public T getData(){
        return data;
    }

    @Override
    public String getMessage(){
        return msg;
    }

    @Override
    public HttpStatus getStatus(){
        return status;
    }

}
