package com.example.project.presentation.form.dish

import com.example.project.presentation.form.material.MaterialForm
import lombok.Data

@Data
data class PutDishForm(
  val dishName: String,
  val createRequiredTime: Int,
  val imageIds: List<String>,
  val materials: List<MaterialForm>,
  val category: List<DishCategoryForm>
)