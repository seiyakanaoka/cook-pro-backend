package com.example.project.presentation.controller.dish

import com.example.project.application.dto.dish.DishDTO
import com.example.project.application.dto.dish.DishProcessesDTO
import com.example.project.application.dto.dish.DishSearchDTO
import com.example.project.application.dto.material.MaterialsDTO
import com.example.project.application.usecase.dish.DishUseCaseImpl
import com.example.project.domain.enums.category.CategoryEnum
import com.example.project.presentation.dto.dish.DishesDTO
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
class DishControllerImpl(private val dishUseCaseImpl: DishUseCaseImpl) {
  /**
   * 料理一覧取得API
   */
  @GetMapping("/dish")
  fun getDishes(
    @RequestAttribute("userId") userId: String,
    @RequestParam(name = "category", required = false) categories: List<CategoryEnum>?
  ): DishesDTO? =
    DishesDTO(dishUseCaseImpl.getDishes(userId, categories))

  /**
   * 料理詳細取得API
   */
  @GetMapping("/dish/{dishId}")
  fun getDishes(@PathVariable dishId: String): DishDTO = dishUseCaseImpl.getDish(dishId)

  /**
   * 料理検索一覧取得API
   */
  @GetMapping("/dish/search")
  fun getSearchDishes(@RequestParam("dishName") dishName: String): List<DishSearchDTO> =
    dishUseCaseImpl.getSearchDishes(dishName)

  /**
   * 料理材料一覧取得API
   */
  @GetMapping("/dish/{dishId}/material")
  fun getMaterials(@PathVariable dishId: String): MaterialsDTO =
    dishUseCaseImpl.getMaterials(dishId)

  /**
   * 料理工程取得API
   */
  @GetMapping("/dish/{dishId}/process")
  fun getProcesses(@PathVariable dishId: String): DishProcessesDTO =
    dishUseCaseImpl.getProcesses(dishId)
}