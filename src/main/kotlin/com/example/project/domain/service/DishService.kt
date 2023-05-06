package com.example.project.domain.service

import com.example.project.domain.dto.DishDto
import com.example.project.domain.dto.DishSearchDto
import com.example.project.domain.entity.Material
import com.example.project.domain.repository.DishRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class DishService(
  private val dishRepository: DishRepository,
) {
  /**
   * 料理一覧を取得する
   */
  fun getDishes(): List<DishDto> = dishRepository.findAllByOrderByCreateTimestampAsc()
    .map { it -> DishDto(it.dishId, it.dishName, null, it.dishCreateRequiredTime) }

  /**
   * 料理詳細を取得する
   */
  fun getDish(dishId: String): DishDto {
    val dish = dishRepository.findById(dishId).orElseThrow { RuntimeException() }
    return DishDto(dish.dishId, dish.dishName, null, dish.dishCreateRequiredTime)
  }

  /**
   * 料理一覧をサジェスト検索用に加工する
   */
  fun getSearchDishes(dishName: String): List<DishSearchDto> =
    dishRepository.findByDishNameContainingOrderByCreateTimestampAsc(dishName)
      .map { it -> DishSearchDto(it.dishId, it.dishName) }

  /**
   * 料理に紐づいた材料一覧を取得する
   */
  fun getMaterials(dishId: String): List<Material> =
    dishRepository.findByMaterials(dishId)
}