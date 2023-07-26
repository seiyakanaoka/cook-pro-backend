package com.example.project.presentation.controller.category

import com.example.project.application.dto.category.CategoriesDto
import com.example.project.application.usecase.category.CategoryUseCase
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
class CategoryControllerImpl(private val categoryUseCase: CategoryUseCase) : CategoryController {
  /**
   * カテゴリー一覧取得API
   */
  @GetMapping("/category")
  override fun getCategories(): CategoriesDto = categoryUseCase.generateCategories()
}