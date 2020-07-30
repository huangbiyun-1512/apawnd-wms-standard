package com.example.poc.controller.v1;

import com.example.poc.api.HealthCheckApi;
import com.example.poc.components.annotation.AutoLogging;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class HealthCheckApiController implements HealthCheckApi {

  @Override
  @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity isAlive() {
    return ResponseEntity.ok(true);
  }
}
