package cn.leta.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by <a href="mailto:xiegengcai@foxmail.com">xiegengcai</a> on 2018/7/23.
 *
 * @author Xie Gengcai
 */
@Slf4j
public class ObjectMapperHelper {
    @Autowired
    private ObjectMapper objectMapper;

    public String writeValueAsString(Object o) {
        try {
            return this.objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
           log.error(e.getMessage());
           return null;
        }
    }

    public byte[] writeValueAsBytes(Object o) {
        try {
            return this.objectMapper.writeValueAsBytes(o);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public <T> T readValue(String content, Class<T> clazz) {
        try {
            return this.objectMapper.readValue(content, clazz);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public <T> T readValue(String content, TypeReference<T> typeReference) {
        try {
            return this.objectMapper.readValue(content, typeReference);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public <T> T readValue(String content, JavaType javaType) {
        try {
            return this.objectMapper.readValue(content, javaType);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
