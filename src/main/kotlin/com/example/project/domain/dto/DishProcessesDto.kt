package com.example.project.domain.dto

import lombok.Data

@Data
data class DishProcessesDto(
  val processes: List<DishProcessDto>,
  val dishName: String
)
