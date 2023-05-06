package com.example.project.domain.service

import com.example.project.domain.dto.CategoriesDto
import com.example.project.domain.enums.CategoryEnum
import org.springframework.stereotype.Service

@Service
class CategoryService() {
  /**
   * カテゴリー一覧を作成する
   */
  fun generateCategories(): CategoriesDto = CategoriesDto(enumValues<CategoryEnum>())
}