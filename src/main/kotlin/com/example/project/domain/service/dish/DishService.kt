package com.example.project.domain.service.dish

import com.example.project.domain.dto.dish.DishDto
import com.example.project.domain.entity.dish.Dish
import com.example.project.domain.repository.dish.DishRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.*

@Service
@RequiredArgsConstructor
class DishService (
  private val dishRepository: DishRepository,
        ) {
  /**
   * 料理一覧を取得する
   */
  fun getDishes(): List<DishDto> = dishRepository.findAll().map { it -> DishDto(it.dishId, it.dishName, null, it.dishCreateRequiredTime) }

  /**
   * 料理詳細を取得する
   */
  fun getDish(dishId: String): DishDto {
    val dish = dishRepository.findById(dishId).orElseThrow { RuntimeException() }
    return DishDto(dish.dishId, dish.dishName, null, dish.dishCreateRequiredTime)
  }

}