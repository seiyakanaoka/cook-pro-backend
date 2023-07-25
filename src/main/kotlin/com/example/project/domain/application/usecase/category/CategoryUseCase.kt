package com.example.project.domain.application.usecase.category

import com.example.project.domain.application.dto.category.CategoriesDto

interface CategoryUseCase {
  fun generateCategories(): CategoriesDto
}