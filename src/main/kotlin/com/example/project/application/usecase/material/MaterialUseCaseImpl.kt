package com.example.project.application.usecase.material

import com.example.project.domain.enums.material.MaterialEnum
import org.springframework.stereotype.Service

@Service
class MaterialUseCaseImpl() : MaterialUseCase {
  override fun getMaterialUnits(): List<MaterialEnum> {
    return enumValues<MaterialEnum>().toList()
  }
}