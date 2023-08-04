package com.example.project.domain.enums.material

import java.util.*

enum class MaterialEnum {
  GRAMS,
  TABLESPOON,
  TEASPOON,
  CC,
  ML,
  PIECE,
  BUNCH,
  CHO,
  PACK,
  SHEET,
  UNIT;

  override fun toString(): String {
    return this.name.lowercase(Locale.getDefault()).replace("_", " ")
  }
}