package com.example.project.domain.repository.dish

import com.example.project.domain.model.category.Category
import com.example.project.domain.model.dish.Dish
import com.example.project.domain.model.dish.DishImage
import com.example.project.domain.model.dish.DishProcess
import com.example.project.domain.model.material.Material
import com.example.project.domain.model.user.User

import org.springframework.data.repository.query.Param
import java.util.*


interface DishRepository {
  fun findById(dishId: String): Optional<Dish>

  fun findByDishCategories(@Param("userId") userId: String, @Param("dishId") dishId: String): List<Category>

  fun findAllByOrderByCreateTimestampDesc(@Param("userId") userId: String): List<Dish>

  fun findByDishNameContainingOrderByCreateTimestampDesc(@Param("userId") userId: String, dishName: String?): List<Dish>

  fun findByDishMaterials(@Param("dishId") dishId: String): List<Material>

  fun findByProcesses(@Param("dishId") dishId: String): List<DishProcess>

  fun findByDishImages(@Param("dishId") dishId: String): List<DishImage>

  fun findByCategoryDishes(
    @Param("userId") userId: String,
    @Param("categoryIds") categoryIds: List<String>
  ): List<Dish>

  fun findByDishesUser(userId: String): User
}