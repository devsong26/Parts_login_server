package parts.login.util;

import lombok.experimental.UtilityClass;
import org.springframework.http.MediaType;
import parts.login.domain.Message;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@UtilityClass
public class ResponseUtil {

    public void setResponseOption(HttpServletResponse response){
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    }

    public PrintWriter getPrintWriter(HttpServletResponse response) throws IOException {
        return response.getWriter();
    }

    public void response(PrintWriter writer, Message message){
        writer.print(message);
    }

}
