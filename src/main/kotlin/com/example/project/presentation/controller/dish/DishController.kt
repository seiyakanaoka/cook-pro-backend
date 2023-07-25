package com.example.project.presentation.controller.dish

import com.example.project.application.dto.dish.DishDTO
import com.example.project.application.dto.dish.DishProcessesDTO
import com.example.project.application.dto.dish.DishSearchDTO
import com.example.project.application.dto.material.MaterialsDTO
import com.example.project.domain.enums.category.CategoryEnum
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

interface DishController {
  fun getDishes(@RequestParam(name = "category", required = false) categories: List<CategoryEnum>?): List<DishDTO>?

  fun getDishes(@PathVariable dishId: String): DishDTO

  fun getSearchDishes(@RequestParam("dishName") dishName: String): List<DishSearchDTO>

  fun getMaterials(@PathVariable dishId: String): MaterialsDTO

  fun getProcesses(@PathVariable dishId: String): DishProcessesDTO
}