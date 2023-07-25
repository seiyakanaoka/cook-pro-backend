package com.example.project.domain.service

import com.example.project.config.aws.CognitoConfig
import com.example.project.config.aws.S3Config
import com.example.project.domain.domain.model.user.User
import com.example.project.domain.domain.repository.user.UserRepository
import com.example.project.domain.dto.UserDto
import com.example.project.domain.form.UserForm
import com.example.project.domain.form.UserNameForm
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminUpdateUserAttributesRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType


@Service
@RequiredArgsConstructor
class UserService(
  private val userRepository: UserRepository,
  private val s3Client: S3Config,
  private val cognitoConfig: CognitoConfig
) {
  private val s3 = s3Client.s3Client()
  private val cognito = cognitoConfig.cognitoClient()

  @Value("\${aws.s3.bucket.name.cooking_app}")
  private val bucketName = ""

  @Value("\${aws.cognito.user-pool-id}")
  private val userPoolId = ""

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
   * ユーザー名編集
   * TODO: CognitoもしくはDBへの変更が失敗した場合、両方とも変更しない構造にする
   */
  fun patchUserName(userId: String, email: String, userNameForm: UserNameForm) {
    val user = userRepository.findById(userId).orElseThrow() { RuntimeException() }
    user.userName = userNameForm.userName
    userRepository.save(user)
    val request: AdminUpdateUserAttributesRequest = AdminUpdateUserAttributesRequest.builder()
      .userPoolId(userPoolId)
      .username(email)
      .userAttributes(
        AttributeType.builder()
          .name("preferred_username")
          .value(userNameForm.userName)
          .build()
      )
      .build()

    cognito.adminUpdateUserAttributes(request)
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