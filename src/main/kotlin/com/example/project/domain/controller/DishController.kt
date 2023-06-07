package com.example.project.domain.controller

import com.example.project.domain.dto.DishDto
import com.example.project.domain.dto.DishProcessesDto
import com.example.project.domain.dto.DishSearchDto
import com.example.project.domain.dto.MaterialsDto
import com.example.project.domain.enums.CategoryEnum
import com.example.project.domain.service.DishService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
class DishController(private val dishService: DishService) {
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
  fun getMaterials(@PathVariable dishId: String): MaterialsDto =
    dishService.getMaterials(dishId)

  /**
   * 料理工程取得API
   */
  @GetMapping("/dish/{dishId}/process")
  fun getProcesses(@PathVariable dishId: String): DishProcessesDto =
    dishService.getProcesses(dishId)
}