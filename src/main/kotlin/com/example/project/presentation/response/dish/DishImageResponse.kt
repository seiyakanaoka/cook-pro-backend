package com.example.project.presentation.response.dish

import lombok.Data

@Data
data class DishImageResponse(
  val id: String,
  val url: String,
)
