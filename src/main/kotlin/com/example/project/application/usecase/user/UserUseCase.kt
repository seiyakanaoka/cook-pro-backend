package com.example.project.application.usecase.user

import com.amazonaws.services.s3.AmazonS3
import com.example.project.application.dto.user.UserDTO
import com.example.project.application.dto.user.UserFormDTO
import com.example.project.form.UserNameForm
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient

interface UserUseCase {
  val s3: AmazonS3
  val cognito: CognitoIdentityProviderClient

  fun createUser(userFormDTO: UserFormDTO): Unit

  fun getUser(userId: String): UserDTO

  fun patchUserName(userId: String, email: String, userNameForm: UserNameForm): Unit

  fun getImageURL(objectKey: String): String
}