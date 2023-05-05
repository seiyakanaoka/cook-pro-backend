package com.example.project.domain.controller

import com.example.project.domain.dto.CategoriesDto
import com.example.project.domain.service.CategoryService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
class CategoryController(private val categoryService: CategoryService) {
  /**
   * カテゴリー一覧取得API
   */
  @GetMapping("/category")
  fun getCategories(): CategoriesDto = categoryService.generateCategories()
}