package com.example.project.application.dto.material

import com.example.project.domain.enums.material.MaterialEnum
import lombok.Data

@Data
data class MaterialDTO(
  val id: String,
  val name: String,
  val quantity: Int,
  val unit: MaterialEnum,
)
