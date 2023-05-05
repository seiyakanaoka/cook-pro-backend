package com.example.project.domain.controller

import com.example.project.domain.entity.Dish
import com.example.project.domain.service.DishService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/vi")
@RequiredArgsConstructor
class DishController (private val dishService: DishService) {
  /**
   * 料理一覧を取得する
   */
  @GetMapping("/dish")
  fun getDishes(): MutableList<Dish> = dishService.getDishes()
}