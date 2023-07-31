package com.example.project.application.usecase.dish

import com.amazonaws.services.s3.AmazonS3
import com.example.project.application.dto.dish.*
import com.example.project.application.dto.material.MaterialsDTO
import com.example.project.domain.enums.category.CategoryEnum

interface DishUseCase {
  val s3: AmazonS3

  fun getDishes(userId: String, categories: List<CategoryEnum>?): List<DishesDTO>?

  fun getDish(userId: String, dishId: String): DishDTO

  fun getSearchDishes(userId: String, dishName: String?): List<DishSearchDTO>

  fun getMaterials(dishId: String): MaterialsDTO

  fun getProcesses(userId: String, dishId: String): DishProcessesDTO

  fun getDishImages(dishId: String): List<DishImageDTO>

  fun getDishImage(dishId: String): DishImageDTO

  fun getImageURL(objectKey: String): String
}