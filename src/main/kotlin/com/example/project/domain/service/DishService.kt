package com.example.project.domain.service

import com.example.project.domain.dto.*
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
  fun getDishes(): List<DishDto> = dishRepository.findAllByOrderByCreateTimestampDesc()
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
    dishRepository.findByDishNameContainingOrderByCreateTimestampDesc(dishName)
      .map { it -> DishSearchDto(it.dishId, it.dishName) }

  /**
   * 料理に紐づいた材料一覧を取得する
   */
  fun getMaterials(dishId: String): MaterialsDto =
    MaterialsDto(
      dishRepository.findByMaterialsOrderByCreateTimestampDesc(dishId)
        .map { it -> MaterialDto(it.materialId, it.materialName) })

  /**
   * 料理に紐づいた料理工程を取得する
   */
  fun getProcesses(dishId: String): DishProcessesDto {
    val processes = dishRepository.findByProcesses(dishId)
      .map { it -> DishProcessDto(it.dishProcessId, it.dishProcessText) }
    val dish = getDish(dishId)
    return DishProcessesDto(processes, dish.dishName)
  }

  /**
   * 料理に紐づいた料理画像を取得する
   */
  fun getDishImages(dishId: String) =
    dishRepository.findByDishImages(dishId).map { it -> DishImageDto(it.dishImageId, it.dishImageKey) }
}