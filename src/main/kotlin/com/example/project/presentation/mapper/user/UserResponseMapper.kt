package com.example.project.presentation.mapper.user

import com.example.project.application.dto.user.UserDTO
import com.example.project.presentation.response.user.UserResponse
import org.springframework.stereotype.Component

@Component
class UserResponseMapper {
  fun toResponse(userDTO: UserDTO): UserResponse {
    return UserResponse(
      userDTO.userName,
      userDTO.image,
      userDTO.email,
      userDTO.telNumber,
      userDTO.displayUserName
    )
  }
}