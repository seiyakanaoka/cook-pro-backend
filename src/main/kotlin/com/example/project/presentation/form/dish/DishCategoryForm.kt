package com.example.project.presentation.form.dish

import com.example.project.domain.enums.category.CategoryEnum
import lombok.Data

@Data
data class DishCategoryForm(
  val categoryId: String,
  val categoryType: CategoryEnum
)
