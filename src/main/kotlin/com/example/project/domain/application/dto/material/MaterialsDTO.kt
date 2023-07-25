package com.example.project.domain.application.dto.material

import lombok.Data

@Data
data class MaterialsDTO(
  val materials: List<MaterialDTO>
)
