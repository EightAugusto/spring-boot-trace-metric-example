package com.eightaugusto.spring.boot.trace.metric.message.controller;

import com.eightaugusto.spring.boot.trace.metric.message.service.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/v1/message")
public class MessageController {

  private final MessageService service;

  @PostMapping
  public void postMessage() {
    log.traceEntry("()");
    service.postMessage();
    log.traceExit();
  }

  @GetMapping("/{message}")
  public void getMessage(@PathVariable String message) {
    log.traceEntry("()");
    service.getMessage(message);
    log.traceExit();
  }
}
