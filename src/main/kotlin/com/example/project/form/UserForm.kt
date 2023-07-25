package com.example.project.form

import lombok.Data


@Data
data class UserForm(
  val userId: String,
  val userName: String,
  val email: String,
  val telNumber: String,
)

