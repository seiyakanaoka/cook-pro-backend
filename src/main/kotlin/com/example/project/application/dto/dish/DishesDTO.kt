package com.example.project.application.dto.dish

import lombok.Data

@Data
data class DishesDTO(
  val dishId: String,
  val dishName: String,
  val image: DishImageDTO,
  val dishCreateRequiredTime: Int
)
