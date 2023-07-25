package com.example.project.application.usecase.user

import com.example.project.application.dto.user.UserDTO
import com.example.project.application.dto.user.UserFormDTO
import com.example.project.application.mapper.user.UserMapper
import com.example.project.config.aws.CognitoConfig
import com.example.project.config.aws.S3Config
import com.example.project.domain.repository.user.UserRepository
import com.example.project.form.UserNameForm
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminUpdateUserAttributesRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType

@Service
@RequiredArgsConstructor
class UserUseCaseImpl(
  private val userRepository: UserRepository,
  private val s3Client: S3Config,
  private val cognitoConfig: CognitoConfig,
  private val userMapper: UserMapper
) : UserUseCase {
  override val s3 = s3Client.s3Client()
  override val cognito = cognitoConfig.cognitoClient()

  @Value("\${aws.s3.bucket.name.cooking_app}")
  private val bucketName = ""

  @Value("\${aws.cognito.user-pool-id}")
  private val userPoolId = ""

  /**
   * 新規登録
   * @param userFormDTO UserFormDTO
   */
  override fun createUser(userFormDTO: UserFormDTO) {
    val user = userMapper.toDomainEntity(userFormDTO)
    userRepository.save(user)
  }

  /**
   * ユーザー情報取得
   * @param userId String
   */
  override fun getUser(userId: String): UserDTO {
    val user = userRepository.findById(userId).orElseThrow { RuntimeException() }
    return userMapper.toDto(user, user.userImageKey?.let { getImageURL(it) })
  }

  /**
   * ユーザー名編集
   * TODO: CognitoもしくはDBへの変更が失敗した場合、両方とも変更しない構造にする
   */
  override fun patchUserName(userId: String, email: String, userNameForm: UserNameForm) {
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
  override fun getImageURL(objectKey: String): String {
    val url = s3.getUrl(bucketName, objectKey) ?: throw IllegalStateException("URL is null")
    return url.toString()
  }
}