package unsl.utils;

import java.util.HashMap;
import java.util.Map;

public class Responses {

    public static final Map<String, String> NOT_FOUND_BODY;

    static {
        NOT_FOUND_BODY = new HashMap<>();
        NOT_FOUND_BODY.put("error", "404");
        NOT_FOUND_BODY.put("message", "resource not found");
    }
}
