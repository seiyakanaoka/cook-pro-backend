package com.example.project.presentation.response.user

import lombok.Data

@Data
data class UserResponse(
  val name: String,
  val image: String?,
  val email: String,
  val telNumber: String,
  val displayUserName: String? = null,
)
