package com.example.controller.v1;

import com.example.poc.api.HealthCheckApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class HealthCheckApiController implements HealthCheckApi {

  @Override
  @GetMapping("/health")
  public ResponseEntity isAlive() {
    return ResponseEntity.ok(true);
  }
}
