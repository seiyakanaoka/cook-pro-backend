package com.example.project.presentation.response.dish

import com.example.project.presentation.response.material.MaterialResponse
import lombok.Data

@Data
data class DishMaterialsResponse(
  val materials: List<MaterialResponse>
)