package com.example.project.domain.form

import lombok.Data
import java.sql.Timestamp
import java.util.*


@Data
data class UserForm(
  val user_id: String = UUID.randomUUID().toString(),
  val last_name: String,
  val first_name: String,
  val email: String,
  val tel_number: String,
  val user_image_key: String,
  val createTimestamp: Timestamp? = Timestamp(Date().time),
  val updateTimestamp: Timestamp? = Timestamp(Date().time),
)

