package com.example.project.presentation.mapper.user

import com.example.project.application.dto.user.UserFormDTO
import com.example.project.presentation.form.user.UserForm

class UserMapper {
  fun toDto(userForm: UserForm): UserFormDTO {
    return UserFormDTO(
      userForm.userId,
      userForm.userName,
      userForm.email,
      userForm.telNumber,
    )
  }
}