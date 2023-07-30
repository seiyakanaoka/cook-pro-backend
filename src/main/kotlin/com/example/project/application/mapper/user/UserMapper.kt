package com.example.project.application.mapper.user

import com.example.project.application.dto.user.UserDTO
import com.example.project.application.dto.user.UserFormDTO
import com.example.project.domain.model.user.User
import org.springframework.stereotype.Component

@Component
class UserMapper {
  fun toDomainEntity(userFormDTO: UserFormDTO): User {
    return User(
      userFormDTO.userId,
      userFormDTO.userName,
      userFormDTO.email,
      userFormDTO.telNumber
    )
  }

  fun toDto(user: User, image: String?): UserDTO {
    return UserDTO(
      user.userName,
      image,
      user.email,
      user.telNumber,
      user.displayUserName
    )
  }
}