package com.example.project.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Data
import java.sql.Timestamp
import java.util.*

@Entity
@Data
@Table(name = "dish")
data class Dish(
  @Id
  @Column(name = "dish_id", updatable = false, nullable = false)
  val dishId: String = UUID.randomUUID().toString(),
  // TODO: リレーション入れる
  val userId: String = UUID.randomUUID().toString(),
  val dishName: String,
  val dishCreateRequiredTime: Int,
  val createTimestamp: Timestamp? = Timestamp(Date().time),
  val updateTimestamp: Timestamp? = Timestamp(Date().time)
)
