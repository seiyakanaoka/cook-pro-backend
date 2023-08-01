package com.example.project.presentation.form.user

import lombok.Data

@Data
data class UserPatchForm(
  val email: String,
  val telNumber: String,
  val displayUserName: String?,
  val imageId: String?,
)

