package com.example.project.application.usecase.image

import com.example.project.presentation.exception.exception.ImageUploadException
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

  @Value("\${image.upload.size}")
  private val imageUploadSize = 0

  @Value("\${image.upload.mimetype.png}")
  private val png = ""

  @Value("\${image.upload.mimetype.jpeg}")
  private val jpeg = ""

  /**
   * 画像アップロード
   * TODO: 偽装対策も加える
   */
  override fun uploadImage(image: MultipartFile): String {
    if (imageUploadSize < image.size) {
      throw ImageUploadException(400, "画像サイズは5MBを超えてはいけません")
    }
    val allowedMimeTypes = listOf(png, jpeg)
    if (image.contentType !in allowedMimeTypes) {
      throw ImageUploadException(400, "許可されていないMIMEタイプです")
    }
    return s3.uploadImage(userObjectKeyPrefix, image)
  }
}