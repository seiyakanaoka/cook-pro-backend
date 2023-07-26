package com.example.project.presentation.form.user

import lombok.Data


@Data
data class UserForm(
  val userName: String,
  val email: String,
  val telNumber: String,
)

