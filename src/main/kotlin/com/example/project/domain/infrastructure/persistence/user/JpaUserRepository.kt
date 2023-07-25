package com.example.project.domain.infrastructure.persistence.user

import com.example.project.domain.domain.model.user.User
import com.example.project.domain.domain.repository.user.UserRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaUserRepository : UserRepository, JpaRepository<User, String> {
}