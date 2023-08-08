package com.example.project.application.mapper.dish

import com.example.project.application.dto.dish.DishFormDTO
import com.example.project.application.dto.dish.PutDishFormDTO
import com.example.project.domain.model.category.Category
import com.example.project.domain.model.dish.Dish
import com.example.project.domain.model.dish.DishImage
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
      dishFormDTO.materials.map { it ->
        Material(
          UUID.randomUUID().toString(),
          it.materialName,
          it.quantity,
          it.unit,
          dish
        )
      }
        .toMutableList()
    dish.materials = materials
    dish.imageIds = dishFormDTO.imageIds.map { it -> DishImage(UUID.randomUUID().toString(), dish, it) }.toMutableList()
    return dish
  }

  fun toPutDishDomainEntity(user: User, dishImages: List<DishImage>, putDishFormDTO: PutDishFormDTO): Dish {
    val dishId = UUID.randomUUID().toString()
    val dish = Dish(dishId, user, putDishFormDTO.dishName, putDishFormDTO.createRequiredTime)
    val categories = putDishFormDTO.category.map { it -> Category(it.categoryId, it.categoryType) }.toMutableList()
    dish.categories = categories
    val materials =
      putDishFormDTO.materials.map { it ->
        Material(
          UUID.randomUUID().toString(),
          it.materialName,
          it.quantity,
          it.unit,
          dish
        )
      }
        .toMutableList()
    dish.materials = materials
    // s3のulrの場合は、既存のimageIdを代入する
    val imageIds = putDishFormDTO.imageIds.mapIndexed { index, imageId ->
      if (imageId.contains("https://cook-pro-")) {
        try {
          val dishImage = dishImages.elementAt(index)
          DishImage(dishImage.dishImageId, dish, dishImage.dishImageKey)
        } catch (e: IndexOutOfBoundsException) {
          throw e
        }
      } else {
        DishImage(UUID.randomUUID().toString(), dish, imageId)
      }
    }.toMutableList()
    dish.imageIds = imageIds
    return dish
  }
}