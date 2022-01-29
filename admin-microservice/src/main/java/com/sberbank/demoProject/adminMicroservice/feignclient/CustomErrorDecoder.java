package com.sberbank.demoProject.adminMicroservice.feignclient;

import com.sberbank.demoProject.adminMicroservice.exception.CoursesFeignClientException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        return new CoursesFeignClientException("Method: " + s + " returned status: " + response.status(), response);
    }
}
