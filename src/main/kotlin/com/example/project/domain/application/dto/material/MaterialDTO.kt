package com.example.project.domain.application.dto.material

import lombok.Data
import java.util.*

@Data
data class MaterialDTO(
  val materialId: String = UUID.randomUUID().toString(),
  val materialName: String,
)
