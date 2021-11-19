package parts.login.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import parts.login.component.builder.MessageBuilder;
import parts.login.domain.Message;
import parts.login.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private MessageBuilder messageBuilder;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        try{
            ResponseUtil.setResponseOption(response);
            PrintWriter writer = ResponseUtil.getPrintWriter(response);
            Message successMsg = messageBuilder.getMessage(request.getLocale(), HttpStatus.NOT_FOUND);
            ResponseUtil.response(writer, successMsg);
        }catch(IOException e){
            e.getStackTrace();
        }
    }
}
