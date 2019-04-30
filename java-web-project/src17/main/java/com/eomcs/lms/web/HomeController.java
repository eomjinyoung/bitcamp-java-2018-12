package com.eomcs.lms.web;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home") // http://.../java-web-project/app/home
public class HomeController {

  final static Logger logger = LogManager.getLogger(HomeController.class);

  @GetMapping
  public void home() {
  }
  
}










