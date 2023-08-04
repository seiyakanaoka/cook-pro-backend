package com.example.project.presentation.form.material

import com.example.project.domain.enums.material.MaterialEnum
import lombok.Data

@Data
data class MaterialForm(
  val materialId: String,
  val materialName: String,
  val quantity: Int,
  val unit: MaterialEnum,
)
