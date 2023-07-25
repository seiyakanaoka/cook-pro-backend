package com.example.project.domain.controller

import com.example.project.domain.form.UserForm
import com.example.project.domain.form.UserNameForm
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
class UserController(private val userService: UserService) {
  /**
   * 新規登録API
   * 本来はjwtなしでリクエスト可能だが、cognitoによる認証を行った後に実行される想定のため
   * cognitoから受け取ったjwtを検証してから実行される
   */
  @PostMapping("/signup")
  fun signup(@RequestBody userForm: UserForm) {
    userService.createUser(userForm)
  }

  @GetMapping("/user")
  fun getUser(@RequestAttribute("userId") userId: String) = userService.getUser(userId)

  @PatchMapping("/user")
  fun patchUser(
    @RequestAttribute("userId") userId: String,
    @RequestAttribute("email") email: String,
    @RequestBody userNameForm: UserNameForm
  ) =
    userService.patchUserName(userId, email, userNameForm)
}