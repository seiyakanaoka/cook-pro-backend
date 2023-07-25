package com.example.project.domain.controller

import com.example.project.domain.application.usecase.category.CategoryUseCaseImpl
import com.example.project.domain.dto.CategoriesDto
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
class CategoryController(private val categoryService: CategoryUseCaseImpl) {
  /**
   * カテゴリー一覧取得API
   */
  @GetMapping("/category")
  fun getCategories(): CategoriesDto = categoryService.generateCategories()
}