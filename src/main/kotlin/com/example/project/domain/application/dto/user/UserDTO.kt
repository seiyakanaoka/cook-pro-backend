package com.example.project.domain.application.dto.user

import com.example.project.domain.domain.model.user.User
import lombok.Data

@Data
data class UserDTO(
  val userName: String,
  val image: String?,
  val email: String,
  val telNumber: String
) {
  companion object {
    fun convert(user: User, userImageKey: String?): UserDTO {
      return UserDTO(user.userName, userImageKey, user.email, user.telNumber)
    }
  }
}

