package parts.login.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import parts.login.component.builder.MessageBuilder;
import parts.login.domain.Message;
import parts.login.service.RedisService;
import parts.login.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private MessageBuilder messageBuilder;

    @Autowired
    private RedisService redisService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {
        try{
            saveSession(req);
            response(req, res);
        }catch (IOException e){
            e.getStackTrace();
        }
    }

    private void saveSession(HttpServletRequest req) throws ClassCastException{
        String username = req.getParameter("username");
        String sessionId = req.getRequestedSessionId();
        redisService.setValue(username, sessionId);
    }

    private void response(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ResponseUtil.setResponseOption(res);
        PrintWriter writer = ResponseUtil.getPrintWriter(res);
        Message successMsg = messageBuilder.getMessage(req.getLocale(), HttpStatus.OK);
        ResponseUtil.response(writer, successMsg);
    }

}
