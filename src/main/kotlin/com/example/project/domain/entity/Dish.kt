package com.example.project.domain.entity

import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "dish")
data class Dish(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val dishId: String,
  val userId: String,
  val title: String,
  val image: String,
  val dishCreateRequiredTime: Int
)