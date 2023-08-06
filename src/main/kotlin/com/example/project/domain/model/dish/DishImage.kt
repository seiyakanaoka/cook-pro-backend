package com.example.project.domain.model.dish

import jakarta.persistence.*
import lombok.Data
import java.util.*

@Entity
@Data
@Table(name = "dish_image")
data class DishImage(
  @Id
  @Column(name = "dish_image_id", updatable = false, nullable = false)
  val dishImageId: String = UUID.randomUUID().toString(),
  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "dish_id") val dish: Dish,
  val dishImageKey: String
)
