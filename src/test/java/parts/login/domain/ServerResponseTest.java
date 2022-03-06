package parts.login.domain;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ServerResponseTest {

    @Test
    public void test(){
        String data = "ServerResponse data";
        Response<String> response = ServerResponse.ok(data);

        assertEquals(data, response.getData());
        assertEquals(HttpStatus.OK, response.getStatus());
    }

}