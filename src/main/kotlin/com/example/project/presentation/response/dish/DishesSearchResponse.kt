package com.example.project.presentation.response.dish

import lombok.Data

@Data
data class DishesSearchResponse(
  val dishesSearch: List<DishSearchResponse>
)
