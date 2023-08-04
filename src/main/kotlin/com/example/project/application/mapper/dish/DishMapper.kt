package com.example.project.application.mapper.dish

import com.example.project.application.dto.dish.DishFormDTO
import com.example.project.domain.model.dish.Dish
import com.example.project.domain.model.user.User
import org.springframework.stereotype.Component
import java.util.*

@Component
class DishMapper {
  fun toDomainEntity(user: User, dishFormDTO: DishFormDTO): Dish {
    val dishId = UUID.randomUUID().toString()
    return Dish(dishId, user, dishFormDTO.dishName, dishFormDTO.createRequiredTime)
  }
}