package parts.login.domain;

import org.springframework.http.HttpStatus;

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

    @Override
    public Response<T> onSuccess(T data) {
        HttpStatus ok = HttpStatus.OK;
        return new ServerResponse<>(ok, data, ok.getReasonPhrase());
    }

    @Override
    public Response<T> onError(T data) {
        HttpStatus serverError = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ServerResponse<>(serverError, data, serverError.getReasonPhrase());
    }
}
