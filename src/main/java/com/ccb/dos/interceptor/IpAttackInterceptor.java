package com.ccb.dos.interceptor;

import com.ccb.dos.service.IProductStrategy;
import com.ccb.dos.util.IpUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author A
 * @date 2020/2/21 22:03
 */
@Component
public class IpAttackInterceptor implements HandlerInterceptor {

  private static final String ERROR_PRINT = "非法的网络请求，ip address: ";
  @Autowired
  IProductStrategy iProductStrategy;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (!iProductStrategy.product(IpUtils.getIPAddress(request))) {
      buildReturnJson(response, IpUtils.getIPAddress(request));
      return false;
    }
    return true;
  }


  private void buildReturnJson(HttpServletResponse response, String ipAddr) throws IOException {
    response.reset();
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json;charset=UTF-8");
    PrintWriter pw = response.getWriter();
    pw.write(ERROR_PRINT + ipAddr);
  }
}