package com.example.project.domain.application.dto.category

import com.example.project.domain.domain.enums.category.CategoryEnum
import lombok.Data

@Data
data class CategoriesDto(
  val Categories: Array<CategoryEnum>
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as CategoriesDto

    if (!Categories.contentEquals(other.Categories)) return false

    return true
  }

  override fun hashCode(): Int {
    return Categories.contentHashCode()
  }
}