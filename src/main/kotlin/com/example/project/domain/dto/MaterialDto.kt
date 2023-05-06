package com.example.project.domain.dto

import lombok.Data
import java.util.*

@Data
data class MaterialDto(
  val materialId: String = UUID.randomUUID().toString(),
  val materialName: String,
)
