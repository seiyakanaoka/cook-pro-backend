package com.example.project.application.usecase.user

import com.example.project.application.dto.user.UserDTO
import com.example.project.application.dto.user.UserFormDTO
import com.example.project.application.dto.user.UserPatchFormDTO

interface UserUseCase {

  fun createUser(userFormDTO: UserFormDTO): Unit

  fun getUser(userId: String): UserDTO

  fun patchUser(userId: String, userPatchFormDTO: UserPatchFormDTO): Unit
}