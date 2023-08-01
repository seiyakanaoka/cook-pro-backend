package com.example.project.util.aws

import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.example.project.config.aws.S3Config
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.net.URL
import java.util.*

@RequiredArgsConstructor
@Component
class S3Impl(private val s3Config: S3Config) : S3 {
  override val s3Client = s3Config.s3Client()

  @Value("\${aws.s3.bucket.name}")
  private val bucketName = ""

  /**
   * S3の指定したバケットの画像urlを取得する
   * @param objectKey オブジェクトキー名
   * @param expirationDate 有効期限(指定しなかった場合、1時間を有効期限とする)
   * */
  override fun getImageURL(
    objectKey: String,
    expirationDate: Date?
  ): String {
    val imageUrlRequest = GeneratePresignedUrlRequest(bucketName, objectKey)
      .withExpiration(expirationDate)
    val url: URL = s3Client.generatePresignedUrl(imageUrlRequest)
    s3Client.shutdown()
    return url.toString()
  }

  /**
   * S3に画像をアップロードする
   * @param prefix 画像のprefix
   * @param multipartFile アップロードする画像
   */
  override fun uploadImage(prefix: String, multipartFile: MultipartFile): Unit {
    // アップロードする画像のヘッダー情報
    val metadata = ObjectMetadata()
    metadata.contentLength = multipartFile.size
    metadata.contentType = multipartFile.contentType

    val inputStream = ByteArrayInputStream(multipartFile.bytes)
    val objectKey = prefix + UUID.randomUUID().toString()

    val request = PutObjectRequest(bucketName, objectKey, inputStream, metadata)
    s3Client.putObject(request)
    s3Client.shutdown()
  }
}