package com.example.project.domain.dto

import lombok.Data

@Data
data class DishProcessDto(
  val dishProcessId: String,
  val dishProcessText: String
)
