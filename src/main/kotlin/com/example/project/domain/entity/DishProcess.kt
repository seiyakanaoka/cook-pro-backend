package com.example.project.domain.entity

import jakarta.persistence.*
import lombok.Data

@Data
@Entity
@Table(name = "dish_process")
data class DishProcess(
  @Id
  @Column(name = "dish_process_id", updatable = false, nullable = false)
  val dish_process_id: String,
  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "dish_id") val dish: Dish,
  val dish_process_text: String
)
