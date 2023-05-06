package com.example.project.domain.entity

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
  val materialId: String = UUID.randomUUID().toString(),
  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "dish_id") val dish: Dish,
  val materialName: String,
  val createTimestamp: Timestamp? = Timestamp(
    Date().time
  ),
  val updateTimestamp: Timestamp? = Timestamp(Date().time)
)