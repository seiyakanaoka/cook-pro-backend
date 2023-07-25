package com.example.project.domain.dto

import com.example.project.domain.domain.model.user.User

data class UserDto(
  val userName: String,
  val image: String?,
  val email: String,
  val telNumber: String
) {
  companion object {
    fun convert(user: User, userImageKey: String?): UserDto {
      return UserDto(user.userName, userImageKey, user.email, user.telNumber)
    }
  }
}
