package com.capgemini.devonfw.module.base.integration;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.base.IntegrationTestApp;
import com.capgemini.devonfw.module.base.integration.handlers.UpperIntegrationHandler;
import com.capgemini.devonfw.module.integration.common.api.Integration;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * @author pparrado
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestApp.class)
@TestPropertySource(locations = "classpath:requestreplytest.properties")
public class DefaultRequestReplyFlowTest extends ComponentTest {

  @Inject
  private Integration integration;

  @Autowired
  ConfigurableApplicationContext ctx;

  private final String qwerty = "qwerty";

  @Test
  public void sendMessageThroughDefaultRequestReplyChannel() throws InterruptedException {

    this.integration.subscribeAndReply(new UpperIntegrationHandler());
    String response = this.integration.sendAndReceive(this.qwerty);
    assertThat(response).isEqualTo(this.qwerty.toUpperCase());
  }

}
