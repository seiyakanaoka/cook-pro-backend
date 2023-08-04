package com.example.project.application.dto.dish

import com.example.project.domain.enums.category.CategoryEnum
import lombok.Data

@Data
data class DishCategoryFormDTO(
  val categoryId: String,
  val categoryType: CategoryEnum
)
