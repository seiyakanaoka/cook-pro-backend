package com.example.project.presentation.controller.dish

import com.example.project.application.dto.dish.DishProcessesDTO
import com.example.project.domain.enums.category.CategoryEnum
import com.example.project.presentation.form.dish.DishForm
import com.example.project.presentation.form.dish.PutDishForm
import com.example.project.presentation.response.dish.*
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

interface DishController {
  fun getDishes(
    @RequestAttribute("userId") userId: String,
    @RequestParam(name = "category", required = false) categories: List<CategoryEnum>?
  ): DishesResponse?

  fun getDish(@RequestAttribute("userId") userId: String, @PathVariable dishId: String): DishDetailResponse

  fun postDish(@RequestAttribute("userId") userId: String, @RequestBody dishForm: DishForm): PostDishResponse

  fun putDish(
    @RequestAttribute("userId") userId: String,
    @PathVariable dishId: String,
    @RequestBody putDishForm: PutDishForm
  ): PutDishResponse

  fun deleteDish(@PathVariable dishId: String): Unit

  fun getSearchDishes(
    @RequestAttribute("userId") userId: String,
    @RequestParam("dishName") dishName: String
  ): DishesSearchResponse

  fun getMaterials(@PathVariable dishId: String): DishMaterialsResponse

  fun getProcesses(@RequestAttribute("userId") userId: String, @PathVariable dishId: String): DishProcessesDTO
}