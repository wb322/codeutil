package [package].tools;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    /**
     * 对象转成json
     *
     * @param object
     * @return
     */
    public static String objectToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper ();
        try {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY );
            String json = objectMapper.writeValueAsString(object);
            return json;
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * json转对象
     *
     * @param c
     * @return
     */
    public static Object jsonToObject(String json, Class c) {
        ObjectMapper objectMapper = new ObjectMapper ();
        try {
            Object obj = objectMapper.readValue(json, c);
            return obj;
        } catch (Exception e) {
        }
        return null;
    }
}
