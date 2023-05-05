package com.example.project.domain.dto

import com.example.project.domain.CategoryEnum
import lombok.Data

@Data
data class CategoryDto(
  val category: CategoryEnum
)
