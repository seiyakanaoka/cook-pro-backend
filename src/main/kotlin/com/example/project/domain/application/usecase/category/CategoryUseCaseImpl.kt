package com.example.project.domain.application.usecase.category

import com.example.project.domain.application.dto.category.CategoriesDto
import com.example.project.domain.domain.enums.category.CategoryEnum
import org.springframework.stereotype.Service

@Service
class CategoryUseCaseImpl() : CategoryUseCase {
  /**
   * カテゴリー一覧を作成する
   */
  override fun generateCategories(): CategoriesDto = CategoriesDto(enumValues<CategoryEnum>())
}