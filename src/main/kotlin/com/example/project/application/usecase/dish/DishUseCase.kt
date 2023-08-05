package com.example.project.application.usecase.dish

import com.amazonaws.services.s3.AmazonS3
import com.example.project.application.dto.dish.*
import com.example.project.application.dto.material.MaterialDTO
import com.example.project.domain.enums.category.CategoryEnum

interface DishUseCase {
  val s3: AmazonS3

  fun getDishes(userId: String, categories: List<CategoryEnum>?): List<DishesDTO>?

  fun getDish(userId: String, dishId: String): DishDTO

  fun postDish(userId: String, dishFormDTO: DishFormDTO): String

  fun putDish(userId: String, dishId: String, dishFormDTO: DishFormDTO): String

  fun deleteDish(dishId: String): Unit

  fun getSearchDishes(userId: String, dishName: String?): List<DishSearchDTO>

  fun getMaterials(dishId: String): List<MaterialDTO>

  fun getProcesses(userId: String, dishId: String): DishProcessesDTO

  fun getDishImages(dishId: String): List<DishImageDTO>

  fun getDishImage(dishId: String): DishImageDTO

  fun getImageURL(objectKey: String): String
}