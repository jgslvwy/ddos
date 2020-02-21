package com.ccb.dos.service.impl;

import com.ccb.dos.config.IpContainerConfig;
import com.ccb.dos.service.IProductStrategy;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author A
 * @date 2020/2/21 22:42
 */
@Component
public class DefaultProductStrategyImpl implements IProductStrategy {

  private static final int ATTACK_TIME_THRESHOLD = 5;

  private static final String TIME_PATTERN = "yyyyMMddHHmm";

  private static final String TIME_ZONE = "+8";

  private static final String COMM_VAR = ",";

  @Autowired
  IpContainerConfig ipContainerConfig;

  @Override
  public boolean product(String ipAddr) {
    ConcurrentMap<String, Integer> ipContainer = ipContainerConfig.getConcurrentMap();

    String visitNumerKey = buildVisitNumerKey(ipAddr);
    if (!ipContainer.containsKey(visitNumerKey) ) {
      ipContainer.put(visitNumerKey, new Integer(1));
      return true;
    }

    Integer visitNumber = ipContainer.get(visitNumerKey);

    if (ATTACK_TIME_THRESHOLD == visitNumber) {
      return false;
    }

    putVisitNumberIfAbsent(ipAddr, visitNumber);
    return true;
  }

  private String buildVisitNumerKey(String ipAddr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
    String dateTime = LocalDateTime.now(ZoneOffset.of(TIME_ZONE)).format(formatter);
    return ipAddr + COMM_VAR + dateTime;
  }

  private void putVisitNumberIfAbsent(String ipAddr, int visitNumber) {
    visitNumber = visitNumber++;
    ConcurrentMap<String, Integer> ipContainer = ipContainerConfig
        .getConcurrentMap();
    ipContainer.putIfAbsent(buildVisitNumerKey(ipAddr), visitNumber);

  }
}
