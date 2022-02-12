package parts.login.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import parts.login.client.RedisClient;
import parts.login.component.builder.MessageBuilder;
import parts.login.domain.CustomUserDetails;
import parts.login.domain.LoginHistory;
import parts.login.domain.Message;
import parts.login.service.LoginHistoryService;
import parts.login.util.HeaderUtil;
import parts.login.util.ResponseUtil;
import parts.login.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private MessageBuilder messageBuilder;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private LoginHistoryService loginHistoryService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication){
        try{
            CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
            details.setUserAgent(HeaderUtil.getUserAgent(req));

            logingHistory(details);

            saveSession(details);
            response(res, details);
        }catch (IOException e){
            e.getStackTrace();
        }
    }

    private void logingHistory(CustomUserDetails details) {
        loginHistoryService.save(
                LoginHistory.builder()
                        .userId(details.getId())
                        .userAgent(details.getUserAgent())
                        .build()
        );
    }

    private void saveSession(CustomUserDetails details) throws ClassCastException{
        redisClient.setValue(details.getToken(), StringUtil.objToJson(details));
    }

    private void response(HttpServletResponse res, CustomUserDetails details) throws IOException {
        ResponseUtil.setResponseOption(res);
        PrintWriter writer = ResponseUtil.getPrintWriter(res);
        Message successMsg = messageBuilder.getMsgLoginOK(res.getLocale(), HttpStatus.OK, details.getToken());
        ResponseUtil.response(writer, successMsg);
    }

}
