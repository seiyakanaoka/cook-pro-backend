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
   * 料理一覧を取得する
   */
  fun getDishes(): MutableList<Dish> = dishRepository.findAll()
}