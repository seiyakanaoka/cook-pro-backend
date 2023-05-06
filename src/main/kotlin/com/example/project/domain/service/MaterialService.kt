package com.example.project.domain.service

import com.example.project.domain.entity.Material
import com.example.project.domain.repository.MaterialRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class MaterialService(private val materialRepository: MaterialRepository) {
  /**
   * 料理IDに紐づいた材料を取得する
   */
  fun getMaterials(dishId: String): List<Material> {
    return materialRepository.findByDishId(dishId)
  }
}