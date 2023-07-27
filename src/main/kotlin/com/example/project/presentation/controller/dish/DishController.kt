package com.example.project.presentation.controller.dish

import com.example.project.application.dto.dish.DishDTO
import com.example.project.application.dto.dish.DishProcessesDTO
import com.example.project.application.dto.dish.DishSearchDTO
import com.example.project.application.dto.material.MaterialsDTO
import com.example.project.domain.enums.category.CategoryEnum
import com.example.project.presentation.dto.dish.DishesDTO
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestParam

interface DishController {
  fun getDishes(
    @RequestAttribute("userId") userId: String,
    @RequestParam(name = "category", required = false) categories: List<CategoryEnum>?
  ): DishesDTO

  fun getDishes(@PathVariable dishId: String): DishDTO

  fun getSearchDishes(@RequestParam("dishName") dishName: String): List<DishSearchDTO>

  fun getMaterials(@PathVariable dishId: String): MaterialsDTO

  fun getProcesses(@PathVariable dishId: String): DishProcessesDTO
}