package com.example.project.domain.application.dto.category

import com.example.project.domain.enums.CategoryEnum
import lombok.Data

@Data
data class CategoryDto(
  val category: CategoryEnum
)
