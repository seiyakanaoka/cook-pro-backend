package com.example.project.application.usecase.dish

import com.example.project.application.dto.dish.*
import com.example.project.application.dto.material.MaterialDTO
import com.example.project.application.dto.material.MaterialsDTO
import com.example.project.config.aws.S3Config
import com.example.project.domain.enums.category.CategoryEnum
import com.example.project.domain.repository.dish.DishRepository
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
  @Value("\${aws.s3.bucket.name}")
  private val bucketName = ""

  override val s3 = s3Client.s3Client()

  /**
   * 料理一覧を取得する
   */
  override fun getDishes(userId: String, categories: List<CategoryEnum>?): List<DishesDTO>? {
    if (categories == null) {
      return dishRepository.findAllByOrderByCreateTimestampDesc(userId)
        .map { it -> DishesDTO(it.dishId, it.dishName, getDishImage(it.dishId), it.dishCreateRequiredTime) }
    } else if (categories.isEmpty()) {
      return listOf<DishesDTO>()
    }
    return dishRepository.findByCategoriesCategoryIdInOrderByCreateTimestampDesc(categories.map { it -> it.name })
      .map { it1 -> DishesDTO(it1.dishId, it1.dishName, getDishImage(it1.dishId), it1.dishCreateRequiredTime) }
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
   * 料理に紐づいた料理画像一覧を取得する
   */
  override fun getDishImages(dishId: String): List<DishImageDTO> {
    return dishRepository.findByDishImages(dishId)
      .map { it -> DishImageDTO(it.dishImageId, getImageURL(it.dishImageKey)) }
  }


  /**
   * 料理に紐づいた料理画像一覧から、最初の要素を取得する
   */
  override fun getDishImage(dishId: String): DishImageDTO {
    return getDishImages(dishId)[0]
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