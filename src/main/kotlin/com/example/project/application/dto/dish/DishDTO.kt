package com.example.project.application.dto.dish

import com.example.project.domain.model.category.Category
import lombok.Data

@Data
data class DishDTO(
  val dishId: String,
  val dishName: String,
  val images: List<DishImageDTO>,
  val dishCreateRequiredTime: Int,
  val categories: List<Category>
)
