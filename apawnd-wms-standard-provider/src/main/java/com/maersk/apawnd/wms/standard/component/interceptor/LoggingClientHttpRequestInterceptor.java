package com.maersk.apawnd.wms.standard.component.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    @NonNull
    public ClientHttpResponse intercept(
            @NonNull HttpRequest httpRequest,
            @NonNull byte[] bytes,
            ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        traceRequest(httpRequest, bytes);
        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, bytes);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) {
        log.debug("=========================== request begin ================================================");
        log.debug("URI         : {}", request.getURI());
        log.debug("Method      : {}", request.getMethod());
        log.debug("Headers     : {}", request.getHeaders());
        log.debug("Request body: {}", new String(body, StandardCharsets.UTF_8));
        log.debug("=========================== request end ================================================");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        log.debug("============================ response begin ==========================================");
        log.debug("Status code  : {}", response.getStatusCode());
        log.debug("Status text  : {}", response.getStatusText());
        log.debug("Headers      : {}", response.getHeaders());
        log.debug("============================ response end =================================================");
    }
}
