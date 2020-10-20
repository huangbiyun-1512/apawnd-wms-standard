package com.maersk.apawnd.wms.standard.config;

import com.maersk.apawnd.wms.standard.component.interceptor.LoggingClientHttpRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate simpleClientRestTemplate(
      @Autowired LoggingClientHttpRequestInterceptor loggingClientHttpRequestInterceptor) {
    RestTemplate restTemplate = new RestTemplate(simpleClientHttpRequestFactory());
    restTemplate.setInterceptors(Collections.singletonList(loggingClientHttpRequestInterceptor));
    return restTemplate;
  }

  @Bean
  public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory() {
    return new SimpleClientHttpRequestFactory();
  }
}
