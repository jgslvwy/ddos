package com.ccb.dos.config;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class IpContainerConfig {

  @Getter
  private ConcurrentMap<String, Integer> concurrentMap;

  @Bean
  public void itIpContainer() {
    concurrentMap = new ConcurrentHashMap();
  }
}
