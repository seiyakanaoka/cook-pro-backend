package com.example.project.domain.service

import com.example.project.domain.entity.Dish
import com.example.project.domain.repository.DishRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class DishService (
  private val dishRepository: DishRepository,
        ) {
  /**
   * ユーザーを取得する
   * */
  fun getDishes(): MutableList<Dish> = dishRepository.findAll()
}