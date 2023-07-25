package com.example.project.domain.domain.enums.category

import java.util.*

enum class CategoryEnum {
  JAPAN_FOOD,
  WESTERN_FOOD,
  CHINESE_FOOD,
  MEAT_DISH,
  FISH_DISH,
  NOODLE,
  RICE,
  SALAD,
  WITH_ALCOHOL;

  override fun toString(): String {
    return this.name.lowercase(Locale.getDefault()).replace("_", " ")
  }
}