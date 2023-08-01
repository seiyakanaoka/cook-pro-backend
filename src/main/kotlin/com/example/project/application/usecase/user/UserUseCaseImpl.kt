package com.example.project.application.usecase.user

import com.example.project.application.dto.user.UserDTO
import com.example.project.application.dto.user.UserFormDTO
import com.example.project.application.dto.user.UserPatchFormDTO
import com.example.project.application.mapper.user.UserMapper
import com.example.project.config.aws.S3Config
import com.example.project.domain.repository.user.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserUseCaseImpl(
  private val userRepository: UserRepository,
  private val s3Client: S3Config,
  private val userMapper: UserMapper
) : UserUseCase {
  override val s3 = s3Client.s3Client()

  @Value("\${aws.s3.bucket.name}")
  private val bucketName = ""

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
  override fun patchUserName(userId: String, userPatchFormDTO: UserPatchFormDTO) {
    val user = userRepository.findById(userId).orElseThrow() { RuntimeException("ユーザーが存在しません") }
    user.displayUserName = userPatchFormDTO.displayUserName
    user.email = userPatchFormDTO.email
    user.telNumber = userPatchFormDTO.telNumber
    userRepository.save(user)
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