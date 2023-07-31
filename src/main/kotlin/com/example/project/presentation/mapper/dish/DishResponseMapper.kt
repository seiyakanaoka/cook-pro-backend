package com.example.project.presentation.mapper.dish

import com.example.project.application.dto.dish.DishSearchDTO
import com.example.project.application.dto.dish.DishesDTO
import com.example.project.presentation.response.dish.*
import org.springframework.stereotype.Component

@Component
class DishResponseMapper {
  fun toDishesResponse(dishesDTO: List<DishesDTO>?): DishesResponse {
    if (dishesDTO == null) {
      return DishesResponse(listOf<DishResponse>())
    }
    return DishesResponse(dishesDTO.map { it ->
      DishResponse(
        it.dishId,
        it.dishName,
        DishImageResponse(it.image.dishImageId, it.image.dishImageUrl),
        it.dishCreateRequiredTime
      )
    })

  }

  fun toSearchResponse(dishesSearch: List<DishSearchDTO>): DishesSearchResponse {
    val dishesSearchResponse = dishesSearch.map { it -> DishSearchResponse(it.dishId, it.dishName) }
    return DishesSearchResponse(dishesSearchResponse)
  }
}