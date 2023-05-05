package com.example.project.domain.dto

import com.example.project.domain.CategoryEnum
import lombok.Data

@Data
data class CategoriesDto(
  val CategoriesDto: Array<CategoryEnum>
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as CategoriesDto

    if (!CategoriesDto.contentEquals(other.CategoriesDto)) return false

    return true
  }

  override fun hashCode(): Int {
    return CategoriesDto.contentHashCode()
  }
}