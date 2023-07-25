package com.example.project.application.dto.dish

import lombok.Data

@Data
data class DishSearchDTO(
  val dishId: String,
  val dishName: String
)
