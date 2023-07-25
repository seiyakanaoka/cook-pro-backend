package com.example.project.domain.application.dto.dish

import lombok.Data

@Data
data class DishDTO(
  val dishId: String,
  val dishName: String,
  val images: List<DishImageDTO>?,
  val dishCreateRequiredTime: Int
)
