package com.example.project.domain.model.material

import com.example.project.domain.enums.material.MaterialEnum
import com.example.project.domain.model.dish.Dish
import jakarta.persistence.*
import lombok.Data
import java.sql.Timestamp
import java.util.*

@Data
@Entity
@Table(name = "material")
data class Material(
  @Id
  @Column(name = "material_id", updatable = false, nullable = false)
  val id: String = UUID.randomUUID().toString(),
  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "dish_id") val dish: Dish,
  val name: String,
  val quantity: Int,
  val unit: MaterialEnum,
  val createTimestamp: Timestamp? = Timestamp(
    Date().time
  ),
  val updateTimestamp: Timestamp? = Timestamp(Date().time)
)