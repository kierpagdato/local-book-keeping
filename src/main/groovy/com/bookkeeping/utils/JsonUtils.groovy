package com.bookkeeping.utils

import com.fasterxml.jackson.databind.ObjectMapper

class JsonUtils {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()

    static def readValue(String json, Class clazz) {
        OBJECT_MAPPER.readValue(json, clazz)
    }
}
