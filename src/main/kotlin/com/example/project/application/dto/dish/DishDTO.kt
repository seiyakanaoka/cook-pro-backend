package com.example.project.application.dto.dish

import lombok.Data

@Data
data class DishDTO(
  val dishId: String,
  val dishName: String,
  val image: List<DishImageDTO>,
  val dishCreateRequiredTime: Int
)
