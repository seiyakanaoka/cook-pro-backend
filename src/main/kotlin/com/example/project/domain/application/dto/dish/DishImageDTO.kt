package com.example.project.domain.application.dto.dish

import lombok.Data

@Data
data class DishImageDTO(
  val dishImageId: String,
  val dishImageUrl: String,
)
