package parts.login.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import parts.login.component.builder.MessageBuilder;
import parts.login.domain.CustomUserDetails;
import parts.login.domain.Message;
import parts.login.service.RedisService;
import parts.login.util.ResponseUtil;
import parts.login.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private MessageBuilder messageBuilder;

    @Autowired
    private RedisService redisService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws IOException, ServletException {
        try{
            CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
            CustomUserDetails.builder.fromToken(details);
            saveSession(details);
            response(res, details);
        }catch (IOException e){
            e.getStackTrace();
        }
    }

    private void saveSession(CustomUserDetails details) throws ClassCastException{
        Map<String, Object> map = new HashMap<>();
        map.put("username", details.getUsername());

        redisService.setValue(details.getToken(), StringUtil.objToJson(map));
    }

    private void response(HttpServletResponse res, CustomUserDetails details) throws IOException {
        ResponseUtil.setResponseOption(res);
        PrintWriter writer = ResponseUtil.getPrintWriter(res);
        Message successMsg = messageBuilder.getMsgLoginOK(res.getLocale(), HttpStatus.OK, details.getToken());
        ResponseUtil.response(writer, successMsg);
    }

}
