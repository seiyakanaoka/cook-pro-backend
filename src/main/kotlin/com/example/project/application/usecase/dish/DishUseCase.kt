package com.example.project.application.usecase.dish

import com.amazonaws.services.s3.AmazonS3
import com.example.project.application.dto.dish.DishDTO
import com.example.project.application.dto.dish.DishImageDTO
import com.example.project.application.dto.dish.DishProcessesDTO
import com.example.project.application.dto.dish.DishSearchDTO
import com.example.project.application.dto.material.MaterialsDTO
import com.example.project.domain.enums.category.CategoryEnum

interface DishUseCase {
  val s3: AmazonS3

  fun getDishes(categories: List<CategoryEnum>?): List<DishDTO>?

  fun getDish(dishId: String): DishDTO

  fun getSearchDishes(dishName: String): List<DishSearchDTO>

  fun getMaterials(dishId: String): MaterialsDTO

  fun getProcesses(dishId: String): DishProcessesDTO

  fun getDishImages(dishId: String): List<DishImageDTO>

  fun getImageURL(objectKey: String): String
}