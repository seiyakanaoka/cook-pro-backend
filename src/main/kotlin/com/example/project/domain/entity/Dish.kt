package com.example.project.domain.entity

import jakarta.persistence.*
import lombok.Data
import java.util.UUID

@Entity
@Data
@Table(name = "dish")
data class Dish(
  @Id
  @Column(name = "dish_id", updatable = false, nullable = false)
  val dishId: String = UUID.randomUUID().toString(),
  val userId: String = UUID.randomUUID().toString(),
  val dishName: String,
  val dishCreateRequiredTime: Int
)
