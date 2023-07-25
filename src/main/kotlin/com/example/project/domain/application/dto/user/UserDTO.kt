package com.example.project.domain.application.dto.user

import lombok.Data

@Data
data class UserDTO(
  val userName: String,
  val image: String?,
  val email: String,
  val telNumber: String
)

