package com.example.project.application.dto.user

import lombok.Data

@Data
data class UserFormDTO(
  val userId: String,
  val userName: String,
  val email: String,
  val telNumber: String,
)
