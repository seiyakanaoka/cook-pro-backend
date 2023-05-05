package com.example.project.domain.dto

import lombok.Data

@Data
data class DishDto(
  val dishId: String,
  val title: String,
  val image: String,
  val dishCreateRequiredTime: Int
)
