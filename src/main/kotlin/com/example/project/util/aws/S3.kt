package com.example.project.util.aws

import com.amazonaws.services.s3.AmazonS3
import org.springframework.web.multipart.MultipartFile
import java.util.*

interface S3 {
  val s3Client: AmazonS3

  fun getImageURL(objectKey: String, expirationDate: Date? = Date(System.currentTimeMillis() + 3600000)): String

  fun uploadImage(prefix: String, multipartFile: MultipartFile): Unit
}