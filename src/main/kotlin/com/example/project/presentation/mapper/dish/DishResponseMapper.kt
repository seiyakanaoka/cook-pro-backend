package com.example.project.presentation.mapper.dish

import com.example.project.application.dto.dish.*
import com.example.project.application.dto.material.MaterialDTO
import com.example.project.presentation.form.dish.DishForm
import com.example.project.presentation.response.dish.*
import com.example.project.presentation.response.material.MaterialResponse
import org.springframework.stereotype.Component

@Component
class DishResponseMapper {
  fun toDishesResponse(dishesDTO: List<DishesDTO>?): DishesResponse {
    if (dishesDTO == null) {
      return DishesResponse(listOf<DishResponse>())
    }
    return DishesResponse(dishesDTO.map { it ->
      DishResponse(
        it.dishId,
        it.dishName,
        DishImageResponse(it.image.dishImageId, it.image.dishImageUrl),
        it.dishCreateRequiredTime
      )
    })
  }

  fun toSearchResponse(dishesSearch: List<DishSearchDTO>): DishesSearchResponse {
    val dishesSearchResponse = dishesSearch.map { it -> DishSearchResponse(it.dishId, it.dishName) }
    return DishesSearchResponse(dishesSearchResponse)
  }

  fun toDetailResponse(dishDTO: DishDTO): DishDetailResponse {
    val newImages = dishDTO.images.map { it -> DishImageResponse(it.dishImageId, it.dishImageUrl) }
    return DishDetailResponse(
      dishDTO.dishId,
      dishDTO.dishName,
      newImages,
      dishDTO.dishCreateRequiredTime,
//      TODO: falseをやめる
      false,
      dishDTO.categories.map { it -> it.categoryType }
    )
  }

  fun toDishMaterials(dishMaterialDTO: List<MaterialDTO>): DishMaterialsResponse {
    val materialsResponse = dishMaterialDTO.map { it -> MaterialResponse(it.id, it.name, it.quantity, it.unit) }
    return DishMaterialsResponse(materialsResponse)
  }

  fun toDishFormDTO(dishForm: DishForm): DishFormDTO {
    val dishCategoryFormDTO = dishForm.category.map { it -> DishCategoryFormDTO(it.categoryId, it.categoryType) }
    return DishFormDTO(
      dishForm.dishName,
      dishForm.createRequiredTime,
      dishForm.imageIds,
      dishForm.materials,
      dishCategoryFormDTO
    )
  }
}