package com.example.project.application.dto.category

import com.example.project.domain.enums.category.CategoryEnum
import lombok.Data

@Data
data class CategoryDto(
  val category: CategoryEnum
)
