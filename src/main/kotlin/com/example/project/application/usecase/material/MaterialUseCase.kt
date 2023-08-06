package com.example.project.application.usecase.material

import com.example.project.domain.enums.material.MaterialEnum

interface MaterialUseCase {
  fun getMaterialUnits(): List<MaterialEnum>
}