package com.example.project.application.usecase.user

import com.example.project.application.dto.user.UserDTO
import com.example.project.application.dto.user.UserFormDTO
import com.example.project.application.dto.user.UserPatchFormDTO
import com.example.project.application.mapper.user.UserMapper
import com.example.project.domain.repository.user.UserRepository
import com.example.project.util.aws.S3
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
@RequiredArgsConstructor
class UserUseCaseImpl(
  private val userRepository: UserRepository,
  private val s3: S3,
  private val userMapper: UserMapper
) : UserUseCase {
  @Value("\${aws.s3.bucket.name}")
  private val bucketName = ""

  @Value("\${aws.s3.bucket.name.object-key-prefix}")
  private val userObjectKeyPrefix = ""

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
    return userMapper.toDto(user, user.userImageKey?.let {
      s3.getImageURL(it)
    })
  }

  /**
   * ユーザー名編集
   * TODO: CognitoもしくはDBへの変更が失敗した場合、両方とも変更しない構造にする
   */
  override fun patchUser(userId: String, userPatchFormDTO: UserPatchFormDTO) {
    val user = userRepository.findById(userId).orElseThrow() { RuntimeException("ユーザーが存在しません") }
    user.displayUserName = userPatchFormDTO.displayUserName
    user.email = userPatchFormDTO.email
    user.telNumber = userPatchFormDTO.telNumber
    val objectKey = userObjectKeyPrefix + UUID.randomUUID().toString()
//    s3.uploadImage(objectKey, )
    userRepository.save(user)
  }
}