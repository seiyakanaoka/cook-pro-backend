package com.example.project.domain.entity

import jakarta.persistence.*
import lombok.Data

@Data
@Entity
@Table(name = "dish_process")
data class DishProcess(
  @Id
  @Column(name = "dish_process_id", updatable = false, nullable = false)
  val dishProcessId: String,
  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "dish_id") val dish: Dish,
  val dishProcessText: String
)
