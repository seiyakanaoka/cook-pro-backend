package com.example.project.domain.application.dto.category

import com.example.project.domain.domain.enums.category.CategoryEnum
import lombok.Data

@Data
data class CategoryDto(
  val category: CategoryEnum
)
