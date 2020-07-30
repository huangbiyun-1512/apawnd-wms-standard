package com.example.controller.v1;

import com.example.poc.api.v1.HealthCheckApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckApiController implements HealthCheckApi {

  @Override
  public boolean isAlive() {
    return true;
  }
}
