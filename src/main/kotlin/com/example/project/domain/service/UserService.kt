package com.example.project.domain.service

import com.example.project.domain.entity.User
import com.example.project.domain.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserService(private val userRepository: UserRepository) {
  /**
   * 新規登録
   */
  fun createUser(user: User) {
    userRepository.save(user)
  }
}