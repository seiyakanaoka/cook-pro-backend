package com.example.project.domain.dto

import lombok.Data

@Data
data class DishDto(
  val dishId: String,
  val dishName: String,
  val images: List<DishImageDto>?,
  val dishCreateRequiredTime: Int
)
