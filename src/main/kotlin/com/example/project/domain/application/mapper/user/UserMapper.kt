package com.example.project.domain.application.mapper.user

import com.example.project.domain.application.dto.user.UserDTO
import com.example.project.domain.application.dto.user.UserFormDTO
import com.example.project.domain.domain.model.user.User

class UserMapper {
  fun toDomainEntity(userFormDTO: UserFormDTO): User {
    return User(
      userFormDTO.userId,
      userFormDTO.userName,
      userFormDTO.email,
      userFormDTO.telNumber
    )
  }

  fun toDto(user: User): UserDTO {
    return UserDTO(
      user.userId,
      user.userName,
      user.email,
      user.telNumber
    )
  }
}