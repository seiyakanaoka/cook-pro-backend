package com.example.project.domain.exception

data class FieldError(
  val fieldName: String,
  val message: String,
)
