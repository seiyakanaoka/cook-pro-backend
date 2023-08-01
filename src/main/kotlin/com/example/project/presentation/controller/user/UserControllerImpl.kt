package com.example.project.presentation.controller.user

import com.example.project.application.usecase.user.UserUseCase
import com.example.project.presentation.form.user.UserForm
import com.example.project.presentation.form.user.UserPatchForm
import com.example.project.presentation.mapper.user.UserFormMapper
import com.example.project.presentation.mapper.user.UserResponseMapper
import com.example.project.presentation.response.user.UserResponse
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
class UserControllerImpl(
  private val userUseCase: UserUseCase,
  private val userMapper: UserFormMapper,
  private val userResponseMapper: UserResponseMapper
) :
  UserController {
  /**
   * 新規登録API
   * 本来はjwtなしでリクエスト可能だが、cognitoによる認証を行った後に実行される想定のため
   * cognitoから受け取ったjwtを検証してから実行される
   */
  @PostMapping("/signup")
  override fun signup(@RequestAttribute("userId") userId: String, @RequestBody userForm: UserForm) =
    userUseCase.createUser(userMapper.toDto(userId, userForm))


  /**
   * ユーザー取得API
   */
  @GetMapping("/user")
  override fun getUser(@RequestAttribute("userId") userId: String): UserResponse =
    userResponseMapper.toResponse(userUseCase.getUser(userId))

  /**
   * ユーザー情報編集API
   */
  @PatchMapping("/user")
  override fun patchUser(
    @RequestAttribute("userId") userId: String,
    @RequestBody userPatchForm: UserPatchForm
  ) =
    userUseCase.patchUserName(userId, userPatchForm)
}