package com.example.poc.api;

import org.springframework.http.ResponseEntity;

public interface HealthCheckApi {

  ResponseEntity isAlive();

}
