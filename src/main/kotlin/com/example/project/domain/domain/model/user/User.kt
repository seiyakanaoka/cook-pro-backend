package com.example.project.domain.domain.model.user

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
  val userId: String,
  val userName: String,
  val email: String,
  val telNumber: String,
  val displayUserName: String? = null,
  val userImageKey: String? = null,
  val createTimestamp: Timestamp? = Timestamp(Date().time),
  val updateTimestamp: Timestamp? = Timestamp(Date().time),
) {
  companion object {
    fun convert(userForm: UserForm): User {
      return User(
        userForm.userId,
        userForm.userName,
        userForm.email,
        userForm.telNumber
      )
    }
  }
}
