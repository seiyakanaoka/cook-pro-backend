package com.example.project.application.mapper.dish

import com.example.project.application.dto.dish.DishFormDTO
import com.example.project.application.dto.dish.PutDishFormDTO
import com.example.project.domain.model.category.Category
import com.example.project.domain.model.dish.Dish
import com.example.project.domain.model.material.Material
import com.example.project.domain.model.user.User
import org.springframework.stereotype.Component
import java.util.*

@Component
class DishMapper {
  fun toDishDomainEntity(user: User, dishFormDTO: DishFormDTO): Dish {
    val dishId = UUID.randomUUID().toString()
    val dish = Dish(dishId, user, dishFormDTO.dishName, dishFormDTO.createRequiredTime)
    val categories = dishFormDTO.category.map { it -> Category(it.categoryId, it.categoryType) }.toMutableList()
    dish.categories = categories
    val materials =
      dishFormDTO.materials.map { it -> Material(it.materialId, it.materialName, it.quantity, it.unit, dish) }
        .toMutableList()
    dish.materials = materials
    return dish
  }

  fun toPutDishDomainEntity(user: User, putDishFormDTO: PutDishFormDTO): Dish {
    val dishId = UUID.randomUUID().toString()
    val dish = Dish(dishId, user, putDishFormDTO.dishName, putDishFormDTO.createRequiredTime)
    val categories = putDishFormDTO.category.map { it -> Category(it.categoryId, it.categoryType) }.toMutableList()
    dish.categories = categories
    val materials =
      putDishFormDTO.materials.map { it -> Material(it.materialId, it.materialName, it.quantity, it.unit, dish) }
        .toMutableList()
    dish.materials = materials
    return dish
  }
}