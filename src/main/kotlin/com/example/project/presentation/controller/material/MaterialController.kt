package com.example.project.presentation.controller.material

import com.example.project.presentation.response.material.GetMaterialUnitsResponse

interface MaterialController {
  fun getMaterialUnits(): GetMaterialUnitsResponse
}