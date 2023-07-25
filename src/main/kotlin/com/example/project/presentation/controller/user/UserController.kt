package com.example.project.presentation.controller.user

import com.example.project.application.dto.user.UserDTO
import com.example.project.presentation.form.user.UserForm
import com.example.project.presentation.form.user.UserNameForm
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody

interface UserController {
  fun signup(@RequestAttribute("userId") userId: String, @RequestBody userForm: UserForm): Unit

  fun getUser(@RequestAttribute("userId") userId: String): UserDTO

  fun patchUser(
    @RequestAttribute("userId") userId: String,
    @RequestAttribute("email") email: String,
    @RequestBody userNameForm: UserNameForm
  ): Unit
}