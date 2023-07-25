package com.example.project.application.dto.dish

import lombok.Data

@Data
data class DishProcessesDTO(
  val processes: List<DishProcessDTO>,
  val dishName: String
)
