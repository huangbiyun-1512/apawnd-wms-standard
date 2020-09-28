package com.maersk.asia.wms.standard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.time.ZoneOffset;
import java.util.TimeZone;

@SpringBootApplication
@ComponentScan(basePackages = {"com.maersk.*"})
@EnableDiscoveryClient
@EnableScheduling
@EnableCaching
public class WmsStandardProviderApplication {

  public static void main(String[] args) {
    SpringApplication.run(WmsStandardProviderApplication.class, args);
  }

  @PostConstruct
  void started() {
    TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
  }

}
