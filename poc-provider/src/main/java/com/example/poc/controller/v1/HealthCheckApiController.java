package com.example.poc.controller.v1;

import com.common.poc.components.annotation.AutoLogging;
import com.example.poc.api.HealthCheckApi;
import com.example.poc.component.constant.MessageConstant;
import com.example.poc.component.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class HealthCheckApiController implements HealthCheckApi {

  private final MessageUtil messageUtil;

  public HealthCheckApiController(MessageUtil messageUtil) {
    this.messageUtil = messageUtil;
  }

  @Override
  @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity isAlive() {
    log.info("code:{}, title:{}, detail:{}",
        messageUtil.getCode(MessageConstant.MESSAGE_KEY_I01_0001),
        messageUtil.getTitle(MessageConstant.MESSAGE_KEY_I01_0001),
        messageUtil.getDetail(MessageConstant.MESSAGE_KEY_I01_0001));

    return ResponseEntity.ok(true);
  }
}
