package com.example.project.presentation.response.dish

import lombok.Data

@Data
data class DishesResponse(
  val dishes: List<DishResponse>?
)
