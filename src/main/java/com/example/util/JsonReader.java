package com.example.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
    public static Map<String, Object> readJsonFile(String path) {
        File jsonFile = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> resultMap = null;
        try {
            resultMap = objectMapper.readValue(jsonFile, new TypeReference<HashMap<String, Object>>() {});
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
