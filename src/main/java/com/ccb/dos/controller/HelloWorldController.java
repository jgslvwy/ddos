package com.ccb.dos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author A
 * @date 2020/2/21 21:42
 */
@RestController
public class HelloWorldController {

  @GetMapping("/v1/helloworld")
  public String helloworld() {
    return "hello world!";
  }

  @PostMapping("/v2/helloworld")
  public String helloworld(@RequestBody String pk) {
    return "hello world!" + pk;
  }
}
