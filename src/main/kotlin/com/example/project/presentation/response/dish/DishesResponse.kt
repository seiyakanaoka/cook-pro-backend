package com.example.project.presentation.response.dish

import com.example.project.application.dto.dish.DishDTO
import lombok.Data

@Data
data class DishesResponse(
  val dishes: List<DishDTO>?
)
