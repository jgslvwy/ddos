package com.ccb.dos.service;

/**
 * 防攻击策略，可自定义
 * @author A
 * @date 2020/2/21 22:39
 */
public interface IProductStrategy {

  boolean product(String ipAddr);

}
