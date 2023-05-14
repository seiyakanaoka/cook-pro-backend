package com.example.project.domain.controller

import com.example.project.domain.form.UserForm
import com.example.project.domain.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
class UserController(private val userService: UserService) {
  /**
   * ユーザー新規登録API
   */
  @GetMapping("/signup")
  fun userSignup(@RequestBody userForm: UserForm): String {
    return userService.userSignUp(userForm)
  }
}