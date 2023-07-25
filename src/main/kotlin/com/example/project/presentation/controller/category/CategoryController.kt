package com.example.project.presentation.controller.category

import com.example.project.application.dto.category.CategoriesDto

interface CategoryController {
  fun getCategories(): CategoriesDto
}