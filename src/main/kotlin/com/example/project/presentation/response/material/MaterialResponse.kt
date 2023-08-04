package com.example.project.presentation.response.material

import com.example.project.domain.enums.material.MaterialEnum
import lombok.Data

@Data
data class MaterialResponse(
  val id: String,
  val name: String,
  val quantity: Int,
  val unit: MaterialEnum,
)