package com.example.project.domain.domain.repository.user

import com.example.project.domain.domain.model.user.User
import java.util.*

interface UserRepository {
  fun save(user: User): Unit
  fun findById(userId: String): Optional<User>
}