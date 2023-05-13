package com.example.project.domain.entity

import com.example.project.domain.enums.CategoryEnum
import jakarta.persistence.*
import lombok.Data
import java.util.*

@Entity
@Data
@Table(name = "category")
data class Category(
  @Id
  @Column(name = "category_id", updatable = false, nullable = false)
  val categoryId: String = UUID.randomUUID().toString(),
  @Enumerated(EnumType.STRING)
  val categoryType: CategoryEnum,
  @ManyToMany(mappedBy = "categories") var dishes: MutableSet<Dish> = mutableSetOf()
)
