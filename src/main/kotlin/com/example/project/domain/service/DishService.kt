package com.example.project.domain.service

import com.example.project.config.aws.S3Config
import com.example.project.domain.dto.*
import com.example.project.domain.repository.DishRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.URL
import java.util.*

@Service
@RequiredArgsConstructor
class DishService(
  private val dishRepository: DishRepository,
  private val s3Client: S3Config
) {
  private val s3 = s3Client.s3Client()

  @Value("\${aws.s3.bucket.name.cooking_app}")
  private val bucketName = ""

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
    val dishImages = getDishImages(dishId)
    getImageURL("fish.jpeg")
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
  fun getDishImages(dishId: String): List<DishImageDto> {
    val dishImageKeys = dishRepository.findByDishImages(dishId).map { it -> it.dishImageKey }
    return dishRepository.findByDishImages(dishId).map { it -> DishImageDto(it.dishImageId, it.dishImageKey) }
  }

  /**
   * S3の指定したバケットの画像urlを取得する
   * @param bucketName バケット名
   * @param objectKey オブジェクトキー名
   * @param expirationDate 有効期限(指定しなかった場合、1時間を有効期限とする)
   * */
  fun getImageURL(objectKey: String): URL? {
    return s3.getUrl(bucketName, objectKey) ?: throw IllegalStateException("URL is null")
  }
}