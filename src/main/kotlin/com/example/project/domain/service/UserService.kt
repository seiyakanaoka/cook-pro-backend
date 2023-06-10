package com.example.project.domain.service

import com.example.project.config.aws.S3Config
import com.example.project.domain.dto.UserDto
import com.example.project.domain.entity.User
import com.example.project.domain.form.UserForm
import com.example.project.domain.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserService(private val userRepository: UserRepository, private val s3Client: S3Config) {
  private val s3 = s3Client.s3Client()

  @Value("\${aws.s3.bucket.name.cooking_app}")
  private val bucketName = ""

  /**
   * 新規登録
   * @param userForm UserForm
   */
  fun createUser(userForm: UserForm) {
    val user = User.convert(userForm)
    userRepository.save(user)
  }

  /**
   * ユーザー情報取得
   * @param userId String
   */
  fun getUser(userId: String): UserDto {
    val user = userRepository.findById(userId).orElseThrow { RuntimeException() }
    return UserDto.convert(user, user.userImageKey?.let { getImageURL(it) })
  }

  /**
   * S3の指定したバケットの画像urlを取得する
   * @param objectKey オブジェクトキー名
   */
  fun getImageURL(objectKey: String): String {
    val url = s3.getUrl(bucketName, objectKey) ?: throw IllegalStateException("URL is null")
    return url.toString()
  }
}