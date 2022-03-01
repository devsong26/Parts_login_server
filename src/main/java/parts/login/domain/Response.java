package parts.login.domain;

public interface Response<T> {

    Response<T> onSuccess(T data);

    Response<T> onError(T data);

}
