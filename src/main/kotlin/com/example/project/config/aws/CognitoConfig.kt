package com.example.project.config.aws

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient


@Configuration
class CognitoConfig {
  @Bean
  fun cognitoClient(): CognitoIdentityProviderClient {
    return CognitoIdentityProviderClient.builder()
      .region(Region.AP_NORTHEAST_1)
      .credentialsProvider(DefaultCredentialsProvider.create())
      .build()
  }
}