package com.capgemini.devonfw.module.base.integration.handlers;

import javax.inject.Named;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import com.capgemini.devonfw.module.integration.common.api.RequestHandler;

@SuppressWarnings("javadoc")
@ConditionalOnProperty(prefix = "devonfw.integration.request-reply", name = "subscriber", havingValue = "true")
@Named("upper-handler")
public class UpperHandler implements RequestHandler {

  @Override
  public Object handleMessage(Message<?> message) {

    if (message.getHeaders().containsKey("header1") && message.getHeaders().containsKey("header2")) {
      System.setProperty("test.header1", message.getHeaders().get("header1").toString());
      System.setProperty("test.header2", message.getHeaders().get("header2").toString());
    }

    System.out.println("in UpperIntegrationHandler. Returning " + message.getPayload().toString().toUpperCase());
    return new GenericMessage<>(message.getPayload().toString().toUpperCase());
  }

}