package com.example.project.application.usecase.user

import com.amazonaws.services.s3.AmazonS3
import com.example.project.application.dto.user.UserDTO
import com.example.project.application.dto.user.UserFormDTO
import com.example.project.application.dto.user.UserPatchFormDTO

interface UserUseCase {
  val s3: AmazonS3

  fun createUser(userFormDTO: UserFormDTO): Unit

  fun getUser(userId: String): UserDTO

  fun patchUserName(userId: String, userPatchFormDTO: UserPatchFormDTO): Unit

  fun getImageURL(objectKey: String): String
}