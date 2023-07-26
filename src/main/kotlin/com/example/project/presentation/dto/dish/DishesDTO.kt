package com.example.project.presentation.dto.dish

import com.example.project.application.dto.dish.DishDTO
import lombok.Data

@Data
data class DishesDTO(
  val dishes: List<DishDTO>?
)
