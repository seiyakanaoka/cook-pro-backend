package com.example.project.controller

import com.example.project.application.dto.dish.DishProcessesDTO
import com.example.project.application.dto.material.MaterialsDTO
import com.example.project.application.usecase.dish.DishUseCaseImpl
import com.example.project.domain.enums.category.CategoryEnum
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
class DishController(private val dishService: DishUseCaseImpl) {
  /**
   * 料理一覧取得API
   */
  @GetMapping("/dish")
  fun getDishes(@RequestParam(name = "category", required = false) categories: List<CategoryEnum>?): List<DishDto>? =
    dishService.getDishes(categories)

  /**
   * 料理詳細取得API
   */
  @GetMapping("/dish/{dishId}")
  fun getDishes(@PathVariable dishId: String): DishDto = dishService.getDish(dishId)

  /**
   * 料理検索一覧取得API
   */
  @GetMapping("/dish/search")
  fun getSearchDishes(@RequestParam("dishName") dishName: String): List<DishSearchDto> =
    dishService.getSearchDishes(dishName)

  /**
   * 料理材料一覧取得API
   */
  @GetMapping("/dish/{dishId}/material")
  fun getMaterials(@PathVariable dishId: String): MaterialsDTO =
    dishService.getMaterials(dishId)

  /**
   * 料理工程取得API
   */
  @GetMapping("/dish/{dishId}/process")
  fun getProcesses(@PathVariable dishId: String): DishProcessesDTO =
    dishService.getProcesses(dishId)
}