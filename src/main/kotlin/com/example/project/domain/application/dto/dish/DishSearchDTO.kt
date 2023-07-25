package com.example.project.domain.application.dto.dish

import lombok.Data

@Data
data class DishSearchDTO(
  val dishId: String,
  val dishName: String
)
