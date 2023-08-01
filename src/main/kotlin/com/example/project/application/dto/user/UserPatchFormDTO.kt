package com.example.project.application.dto.user

import lombok.Data

@Data
data class UserPatchFormDTO(
  val email: String,
  val telNumber: String,
  val displayUserName: String?,
  val userImage: String?,
)
