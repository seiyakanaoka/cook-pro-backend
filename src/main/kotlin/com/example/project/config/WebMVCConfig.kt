package com.example.project.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMVCConfig {
  @Value("\${cors.originPatterns:default}")
  private val corsOriginPatterns: String = ""

  @Bean
  fun addCorsConfig(): WebMvcConfigurer {
    return object : WebMvcConfigurer {
      override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
          .allowedMethods("*")
          .allowedOrigins(corsOriginPatterns)
          .allowCredentials(true)
      }
    }
  }
}