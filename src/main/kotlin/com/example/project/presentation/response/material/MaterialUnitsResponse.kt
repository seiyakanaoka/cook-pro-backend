package com.example.project.presentation.response.material

import com.example.project.domain.enums.material.MaterialEnum
import lombok.Data

@Data
data class MaterialUnitsResponse(
  val materialUnit: List<MaterialEnum>
)
