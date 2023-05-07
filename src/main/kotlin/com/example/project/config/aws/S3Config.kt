package com.example.project.config.aws

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Config {
  /**
   * ローカルからクレデンシャル情報を取得してS3を操作する設定
   */
  @Bean
  fun s3Client(): AmazonS3 {
    return AmazonS3ClientBuilder.defaultClient()
  }
}