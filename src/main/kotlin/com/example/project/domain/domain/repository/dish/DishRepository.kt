package com.example.project.domain.domain.repository.dish

import com.example.project.domain.entity.Dish
import com.example.project.domain.entity.DishImage
import com.example.project.domain.entity.DishProcess
import com.example.project.domain.entity.Material
import org.springframework.data.repository.query.Param


interface DishRepository {
  fun findAllByOrderByCreateTimestampDesc(): List<Dish>
  fun findByDishNameContainingOrderByCreateTimestampDesc(dishName: String): List<Dish>
  fun findByMaterialsOrderByCreateTimestampDesc(@Param("dishId") dishId: String): List<Material>
  fun findByProcesses(@Param("dishId") dishId: String): List<DishProcess>
  fun findByDishImages(@Param("dishId") dishId: String): List<DishImage>
  fun findByCategoriesCategoryIdInOrderByCreateTimestampDesc(categoryIds: List<String>): List<Dish>
}