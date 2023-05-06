package com.example.project.domain.dto

import com.example.project.domain.enums.CategoryEnum
import lombok.Data

@Data
data class CategoryDto(
  val category: CategoryEnum
)
