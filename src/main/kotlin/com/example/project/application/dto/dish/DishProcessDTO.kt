package com.example.project.application.dto.dish

import lombok.Data

@Data
data class DishProcessDTO(
  val dishProcessId: String,
  val dishProcessText: String
)
