package parts.login.component.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import parts.login.domain.Message;

import java.util.Locale;

@Component
public class MessageBuilder {

    @Autowired
    private MessageSource messageSource;

    private final String msgKeyPrefix = "msg";

    public Message getMessage(Locale locale, HttpStatus status) {
        String msgKey = getMsgKey(status);
        return build(status, msgKey, locale);
    }

    private String getMsgKey(HttpStatus status){
        return new StringBuilder(msgKeyPrefix)
                .append(".")
                .append(status.value())
                .toString();
    }

    private Message build(HttpStatus status, String msgKey, Locale locale){
        Message msg = new Message();
        msg.setStatus(status.name());
        msg.setData(null);
        msg.setMessage(messageSource.getMessage(msgKey, null, locale));
        return msg;
    }

}
