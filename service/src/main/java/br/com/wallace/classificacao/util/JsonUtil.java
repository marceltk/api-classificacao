package br.com.wallace.classificacao.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonUtil {

    private static final String DEFAULT_LOG = "{}";

    private JsonUtil() {

    }

    public static Object readJson(String json, Class cls) {
        try {
            return new ObjectMapper().readValue(json, cls);
        } catch (Exception e) {
            log.error(DEFAULT_LOG, e);
            return null;
        }
    }

    public static String writeJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            log.error(DEFAULT_LOG, e);
            return null;
        }
    }
}
