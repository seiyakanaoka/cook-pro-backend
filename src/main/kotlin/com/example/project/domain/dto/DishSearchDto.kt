package com.example.project.domain.dto

import lombok.Data

@Data
data class DishSearchDto(
  val dishId: String,
  val title: String
)
