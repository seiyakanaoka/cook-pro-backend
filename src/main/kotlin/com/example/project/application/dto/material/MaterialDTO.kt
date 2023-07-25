package com.example.project.application.dto.material

import lombok.Data
import java.util.*

@Data
data class MaterialDTO(
  val materialId: String = UUID.randomUUID().toString(),
  val materialName: String,
)
