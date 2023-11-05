package com.eightaugusto.spring.boot.trace.metric.message.service.impl;

import com.eightaugusto.spring.boot.trace.metric.message.service.MessageService;
import java.net.InetAddress;
import java.net.URI;
import java.util.UUID;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class MessageServiceImpl implements MessageService {

  private static final String LOCAL_HOST_URI_PATTERN = "%s:%d";
  private static final String POST_MESSAGE_PATH = "http://%s/v1/message/%s";

  private final RestTemplate restTemplate;
  private final String localUri;

  @SneakyThrows
  public MessageServiceImpl(
      RestTemplate restTemplate, @Value("${server.port:-8080}") int serverPort) {
    this.restTemplate = restTemplate;
    this.localUri =
        String.format(
            LOCAL_HOST_URI_PATTERN, InetAddress.getLocalHost().getHostAddress(), serverPort);
  }

  @Override
  public void getMessage(String message) {
    log.traceEntry("({})", message);
    log.info("Received message: '{}'", message);
    log.traceExit();
  }

  @Override
  @SneakyThrows
  public void postMessage() {
    log.traceEntry("()");
    restTemplate.getForEntity(
        new URI(String.format(POST_MESSAGE_PATH, localUri, UUID.randomUUID())), Void.class);
    log.traceExit();
  }
}
