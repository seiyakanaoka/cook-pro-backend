package com.example.project.domain.form

import lombok.Data

@Data
data class UserForm(
  val lastName: String,
  val firstName: String,
  val lastNameKana: String,
  val firstNameKana: String,
  val userName: String,
  val email: String,
  val password: String,
  val telNumber: String
)
