package com.example.project.presentation.controller.user

import com.example.project.presentation.form.user.UserForm
import com.example.project.presentation.form.user.UserPatchForm
import com.example.project.presentation.response.user.UserResponse
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody

interface UserController {
  fun signup(@RequestAttribute("userId") userId: String, @RequestBody userForm: UserForm): Unit

  fun getUser(@RequestAttribute("userId") userId: String): UserResponse

  fun patchUser(
    @RequestAttribute("userId") userId: String,
    @RequestBody userPatchForm: UserPatchForm
  ): Unit
}