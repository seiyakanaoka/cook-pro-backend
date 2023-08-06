package com.example.project.presentation.controller.material

import com.example.project.presentation.response.material.GetMaterialUnitsResponse
import org.springframework.web.bind.annotation.RestController

@RestController
class MaterialControllerImpl() : MaterialController {
  override fun getMaterialUnits(): GetMaterialUnitsResponse {
    return GetMaterialUnitsResponse(listOf())
  }
}