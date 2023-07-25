package com.example.project.presentation.controller.user

import com.example.project.application.usecase.user.UserUseCase
import com.example.project.presentation.form.user.UserForm
import com.example.project.presentation.form.user.UserNameForm
import com.example.project.presentation.mapper.user.UserMapper
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
class UserControllerImpl(private val userUseCase: UserUseCase, private val userMapper: UserMapper) : UserController {
  /**
   * 新規登録API
   * 本来はjwtなしでリクエスト可能だが、cognitoによる認証を行った後に実行される想定のため
   * cognitoから受け取ったjwtを検証してから実行される
   */
  @PostMapping("/signup")
  override fun signup(@RequestBody userForm: UserForm) = userUseCase.createUser(userMapper.toDto(userForm))


  /**
   * ユーザー取得API
   */
  @GetMapping("/user")
  override fun getUser(@RequestAttribute("userId") userId: String) = userUseCase.getUser(userId)

  /**
   * ユーザー情報編集API
   */
  @PatchMapping("/user")
  override fun patchUser(
    @RequestAttribute("userId") userId: String,
    @RequestAttribute("email") email: String,
    @RequestBody userNameForm: UserNameForm
  ) =
    userUseCase.patchUserName(userId, email, userNameForm)
}