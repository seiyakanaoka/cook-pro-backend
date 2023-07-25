package com.example.project.application.usecase.category

import com.example.project.application.dto.category.CategoriesDto

interface CategoryUseCase {
  fun generateCategories(): CategoriesDto
}