package com.example.project.presentation.form.user

import lombok.Data

@Data
data class UserPatchForm(
  val email: String,
  val telNumber: String,
  val displayName: String?,
  val imageId: String?,
)

