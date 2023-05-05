package com.example.project.domain.dto.dish

import lombok.Data
import java.util.*

@Data
data class DishDto(
  val dishId: String,
  val title: String,
  val image: String?,
  val dishCreateRequiredTime: Int
)
