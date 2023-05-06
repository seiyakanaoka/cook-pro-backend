package com.example.project.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import lombok.Data
import java.sql.Timestamp
import java.util.*

@Data
@Entity
@Table(name = "material")
data class Material(
  val materialId: String = UUID.randomUUID().toString(),
  @ManyToOne(fetch = FetchType.LAZY) val dishId: String,
  val materialName: String,
  val createTimestamp: Timestamp? = Timestamp(
    Date().time
  ),
  val updateTimestamp: Timestamp? = Timestamp(Date().time)
)