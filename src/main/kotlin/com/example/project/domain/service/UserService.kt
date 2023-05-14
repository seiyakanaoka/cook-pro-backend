package com.example.project.domain.service

import com.example.project.domain.form.UserForm
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserService {
  /**
   * ユーザー新規登録
   */
  fun userSignUp(userForm: UserForm) {}
}