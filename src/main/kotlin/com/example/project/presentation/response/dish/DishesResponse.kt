package com.example.project.presentation.response.dish

import com.example.project.application.dto.dish.DishesDTO
import lombok.Data

@Data
data class DishesResponse(
  val dishes: List<DishesDTO>?
)
