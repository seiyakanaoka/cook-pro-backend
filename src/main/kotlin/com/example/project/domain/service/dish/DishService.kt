package com.example.project.domain.service.dish

import com.example.project.domain.dto.dish.DishDto
import com.example.project.domain.repository.dish.DishRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class DishService (
  private val dishRepository: DishRepository,
        ) {
  /**
   * 料理一覧を取得する
   */
  fun getDishes(): List<DishDto> = dishRepository.findAll().map { it -> DishDto(it.dishId, it.dishName, null, it.dishCreateRequiredTime) }

}