package com.example.project.domain.service

import com.example.project.config.aws.CognitoConfig
import com.example.project.config.aws.S3Config
import com.example.project.domain.dto.UserDto
import com.example.project.domain.entity.User
import com.example.project.domain.form.UserForm
import com.example.project.domain.form.UserNameForm
import com.example.project.domain.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminUpdateUserAttributesRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminUpdateUserAttributesResponse
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType


@Service
@RequiredArgsConstructor
class UserService(
  private val userRepository: UserRepository,
  private val s3Client: S3Config,
  private val cognitoConfig: CognitoConfig
) {
  private val s3 = s3Client.s3Client()
  private val cognito = cognitoConfig.cognitoConfig()

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
   * ユーザー名編集
   */
  fun patchUserName(userId: String, userNameForm: UserNameForm) {
    val user = userRepository.findById(userId).orElseThrow() { RuntimeException() }
    user.userName = userNameForm.userName
    userRepository.save(user)
    val request: AdminUpdateUserAttributesRequest = AdminUpdateUserAttributesRequest.builder()
      .userPoolId("ap-northeast-1_RcfdArzNy")
      .username("test-user-1")
      .userAttributes(
        AttributeType.builder()
          .name("preferred_username")
          .value(userNameForm.userName)
          .build()
      )
      .build()
    val response: AdminUpdateUserAttributesResponse = cognito.adminUpdateUserAttributes(request)
    println("response : $response")
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