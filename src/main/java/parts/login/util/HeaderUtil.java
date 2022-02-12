package parts.login.util;

import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;

@UtilityClass
public class HeaderUtil {

    enum HeaderName {
        USER_AGENT("user-agent")
        ;

        private String name;
        HeaderName(String name){
            this.name = name;
        }
    }


    public String getUserAgent(HttpServletRequest req){
        return extractHeader(req, HeaderName.USER_AGENT);
    }

    private String extractHeader(HttpServletRequest req, HeaderName headerName){
        return req.getHeader(headerName.name);
    }

}
