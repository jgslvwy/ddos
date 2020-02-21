package com.ccb.dos.service;

/**
 * 定时回收concurrent容器的内容
 *
 * @author jgs
 * @date 2020-02-21 23:15
 * @param: null
 * @return
 * @since 1.0
 */
public interface IContainerGarbageCollector {

  void clean();
}
