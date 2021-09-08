package com.rakuriku.rakuriku.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {
  @GetMapping(path = "/")
  public String getSpa() {
    return "forward:/200.html";
  }
}
