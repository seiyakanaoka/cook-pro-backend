package com.example.project.presentation.response.dish

import lombok.Data

@Data
data class DishesSearchResponse(
  val id: String,
  val name: String
)
