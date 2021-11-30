package parts.login.util;

import com.google.gson.Gson;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {

    private Gson gson = new Gson();

    public String objToJson(Object obj){
        return gson.toJson(obj);
    }

}
