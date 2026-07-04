package com.banking.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConfigServiceApplicationTest {

  @Autowired
  private EnvironmentRepository environmentRepository;

  @Test
  void shouldLoadAllServiceConfigurations() {
    assertConfigurationExists("account-service");
    assertConfigurationExists("authentication-service");
    assertConfigurationExists("mfa-service");
  }

  private void assertConfigurationExists(String applicationName) {
    Environment environment = environmentRepository.findOne(applicationName, "default", null);

    assertThat(environment.getPropertySources()).isNotEmpty();
  }
}
