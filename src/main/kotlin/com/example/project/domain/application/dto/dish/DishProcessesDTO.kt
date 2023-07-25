package com.example.project.domain.application.dto.dish

import lombok.Data

@Data
data class DishProcessesDTO(
  val processes: List<DishProcessDTO>,
  val dishName: String
)
