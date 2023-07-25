package com.example.project.domain.application.usecase.dish

import com.example.project.config.aws.S3Config
import com.example.project.domain.application.dto.dish.*
import com.example.project.domain.application.dto.material.MaterialDTO
import com.example.project.domain.application.dto.material.MaterialsDTO
import com.example.project.domain.domain.repository.dish.DishRepository
import com.example.project.domain.dto.*
import com.example.project.domain.enums.CategoryEnum
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
@RequiredArgsConstructor
class DishUseCaseImpl(
  private val dishRepository: DishRepository,
  private val s3Client: S3Config
) : DishUseCase {
  @Value("\${aws.s3.bucket.name.cooking_app}")
  private val bucketName = ""

  override val s3 = s3Client.s3Client()

  /**
   * 料理一覧を取得する
   */
  override fun getDishes(categories: List<CategoryEnum>?): List<DishDTO>? {
    if (categories == null) {
      return dishRepository.findAllByOrderByCreateTimestampDesc()
        .map { it -> DishDTO(it.dishId, it.dishName, getDishImages(it.dishId), it.dishCreateRequiredTime) }
    } else if (categories.isEmpty()) {
      return listOf<DishDTO>()
    }
    return dishRepository.findByCategoriesCategoryIdInOrderByCreateTimestampDesc(categories.map { it -> it.name })
      .map { it1 -> DishDTO(it1.dishId, it1.dishName, getDishImages(it1.dishId), it1.dishCreateRequiredTime) }
  }

  /**
   * 料理詳細を取得する
   */
  override fun getDish(dishId: String): DishDTO {
    val dish = dishRepository.findById(dishId).orElseThrow { RuntimeException() }
    return DishDTO(dish.dishId, dish.dishName, getDishImages(dishId), dish.dishCreateRequiredTime)
  }

  /**
   * 料理一覧をサジェスト検索用に加工する
   */
  override fun getSearchDishes(dishName: String): List<DishSearchDTO> =
    dishRepository.findByDishNameContainingOrderByCreateTimestampDesc(dishName)
      .map { it -> DishSearchDTO(it.dishId, it.dishName) }

  /**
   * 料理に紐づいた材料一覧を取得する
   */
  override fun getMaterials(dishId: String): MaterialsDTO =
    MaterialsDTO(
      dishRepository.findByMaterialsOrderByCreateTimestampDesc(dishId)
        .map { it -> MaterialDTO(it.materialId, it.materialName) })

  /**
   * 料理に紐づいた料理工程を取得する
   */
  override fun getProcesses(dishId: String): DishProcessesDTO {
    val processes = dishRepository.findByProcesses(dishId)
      .map { it -> DishProcessDTO(it.dishProcessId, it.dishProcessText) }
    val dish = getDish(dishId)
    return DishProcessesDTO(processes, dish.dishName)
  }

  /**
   * 料理に紐づいた料理画像を取得する
   */
  override fun getDishImages(dishId: String): List<DishImageDTO> {
    return dishRepository.findByDishImages(dishId)
      .map { it -> DishImageDTO(it.dishImageId, getImageURL(it.dishImageKey)) }
  }

  /**
   * S3の指定したバケットの画像urlを取得する
   * @param objectKey オブジェクトキー名
   */
  override fun getImageURL(objectKey: String): String {
    val url = s3.getUrl(bucketName, objectKey) ?: throw IllegalStateException("URL is null")
    return url.toString()
  }
}