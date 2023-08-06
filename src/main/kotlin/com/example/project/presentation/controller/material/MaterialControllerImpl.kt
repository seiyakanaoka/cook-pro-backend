package com.example.project.presentation.controller.material

import com.example.project.application.usecase.material.MaterialUseCase
import com.example.project.presentation.response.material.GetMaterialUnitsResponse
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequiredArgsConstructor
@RequestMapping("api/v1")
@RestController
class MaterialControllerImpl(private val materialUseCase: MaterialUseCase) : MaterialController {
  /**
   * 料理材料単位一覧取得API
   */
  @GetMapping("/material/units")
  override fun getMaterialUnits(): GetMaterialUnitsResponse {
    val materialUnits = materialUseCase.getMaterialUnits()
    return GetMaterialUnitsResponse(materialUnits)
  }
}