package com.example.project.presentation.response.dish

import com.example.project.domain.model.category.Category
import lombok.Data

@Data
data class DishDetailResponse(
  val id: String,
  val name: String,
  val images: List<DishImageResponse>,
  val createRequiredTime: Int,
  val favoriteFlag: Boolean,
  val categories: MutableSet<Category>
)
