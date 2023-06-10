package com.example.project.domain.entity

import com.example.project.domain.form.UserForm
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
  val userId: String = UUID.randomUUID().toString(),
  val lastName: String,
  val firstName: String,
  val userName: String,
  val email: String,
  val telNumber: String,
  val user_image_key: String? = null,
  val createTimestamp: Timestamp? = Timestamp(Date().time),
  val updateTimestamp: Timestamp? = Timestamp(Date().time),
) {
  companion object {
    fun convert(userForm: UserForm): User {
      return User(
        UUID.randomUUID().toString(),
        userForm.lastName,
        userForm.firstName,
        userForm.userName,
        userForm.email,
        userForm.telNumber
      )
    }
  }
}
