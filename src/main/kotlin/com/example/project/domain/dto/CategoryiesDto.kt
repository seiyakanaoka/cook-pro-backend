package com.example.project.domain.dto

import lombok.Data

@Data
data class CategoriesDto(
  val CategoriesDto: MutableList<CategoryDto>
)