package com.example.project.domain.domain.model.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Data
import java.sql.Timestamp
import java.util.*

@Entity
@Data
@Table(name = "user")
data class User(
  @Id
  @Column(name = "user_id", updatable = false, nullable = false)
  val userId: String,
  var userName: String,
  val email: String,
  val telNumber: String,
  val displayUserName: String? = null,
  val userImageKey: String? = null,
  val createTimestamp: Timestamp? = Timestamp(Date().time),
  val updateTimestamp: Timestamp? = Timestamp(Date().time),
)
