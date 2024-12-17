package com.philips.itaap.supplychainit.ngh.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class HandlerUtils {

    private static ObjectMapper mapper = new ObjectMapper();

//    private static BasicJsonParser parser = new BasicJsonParser();

//    public static String writeValueAsString(Object inputObject) throws JsonProcessingException {
//        return mapper.writeValueAsString(inputObject);
//    }

    public static <T> T readValue(String inputString, Class<T> type) throws JsonProcessingException {
        return mapper.readValue(inputString, type);
    }

//    public static Map<String, Object> parseMap(String inputString) {
//        return parser.parseMap(inputString);
//    }

}
