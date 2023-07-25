package com.example.project.domain.repository.dish

import com.example.project.domain.model.dish.Dish
import com.example.project.domain.model.dish.DishImage
import com.example.project.domain.model.dish.DishProcess
import com.example.project.domain.model.material.Material
import org.springframework.data.repository.query.Param
import java.util.*


interface DishRepository {
  fun findById(dishId: String): Optional<Dish>
  fun findAllByOrderByCreateTimestampDesc(): List<Dish>
  fun findByDishNameContainingOrderByCreateTimestampDesc(dishName: String): List<Dish>
  fun findByMaterialsOrderByCreateTimestampDesc(@Param("dishId") dishId: String): List<Material>
  fun findByProcesses(@Param("dishId") dishId: String): List<DishProcess>
  fun findByDishImages(@Param("dishId") dishId: String): List<DishImage>
  fun findByCategoriesCategoryIdInOrderByCreateTimestampDesc(categoryIds: List<String>): List<Dish>
}