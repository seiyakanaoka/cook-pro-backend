package com.example.project.presentation.mapper.dish

import com.example.project.application.dto.dish.DishSearchDTO
import com.example.project.presentation.response.dish.DishSearchResponse
import org.springframework.stereotype.Component

@Component
class DishResponseMapper {
  fun toSearchResponse(dishesSearch: List<DishSearchDTO>): List<DishSearchResponse> {
    return dishesSearch.map { it -> DishSearchResponse(it.dishId, it.dishName) }
  }
}