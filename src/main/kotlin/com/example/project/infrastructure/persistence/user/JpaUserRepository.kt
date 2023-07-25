package com.example.project.infrastructure.persistence.user

import com.example.project.domain.model.user.User
import com.example.project.domain.repository.user.UserRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaUserRepository : UserRepository, JpaRepository<User, String> {
}