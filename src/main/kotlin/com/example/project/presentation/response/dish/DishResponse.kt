package com.example.project.presentation.response.dish

import lombok.Data

@Data
data class DishResponse(
  val id: String,
  val name: String,
  val image: DishImageResponse,
  val createRequiredTime: Int
)
