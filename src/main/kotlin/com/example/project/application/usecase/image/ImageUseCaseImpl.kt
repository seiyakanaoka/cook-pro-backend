package com.example.project.application.usecase.image

import com.example.project.util.aws.S3
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@RequiredArgsConstructor
@Service
class ImageUseCaseImpl(private val s3: S3) : ImageUseCase {
  @Value("\${aws.s3.object-key-prefix}")
  private val userObjectKeyPrefix = ""

  override fun uploadImage(image: MultipartFile): String {
    return s3.uploadImage(userObjectKeyPrefix, image)
  }
}