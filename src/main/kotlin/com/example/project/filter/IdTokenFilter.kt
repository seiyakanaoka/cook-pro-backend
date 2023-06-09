package com.example.project.filter

import com.example.project.util.IdTokenValidator
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class IdTokenFilter(private val idTokenValidator: IdTokenValidator) : Filter {

  override fun doFilter(servletRequest: ServletRequest?, servletResponse: ServletResponse?, chain: FilterChain?) {
    var request = servletRequest as? HttpServletRequest
    var response = servletResponse as? HttpServletResponse
    val header = request?.getHeader("Authorization")
    // Authorizationヘッダーがnullの場合はエラー
    if (header == null) {
      response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ログインしてください。")
      return;
    }

    // トークンがなかったらエラー
    val token = header.replaceFirst("Bearer ", "");
    if (token == "") {
      response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ログインしてください。")
      return;
    }

    // デコードできなかったらエラー
    if (idTokenValidator.idTokenVerify(token) == null) {
      response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "不正な操作です。")
      return;
    }

    // トークンテーブルにトークンが存在してなかったらエラー
    // TODO: ユーザーが存在するかを確認する処理を追加する
//    if (!tokenRepository.existsByToken(token)) {
//      response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ログインしてください。")
//      return;
//    }

    chain?.doFilter(servletRequest, servletResponse);

  }

  @Bean
  fun filter(): FilterRegistrationBean<IdTokenFilter>? {
    val bean: FilterRegistrationBean<IdTokenFilter> = FilterRegistrationBean<IdTokenFilter>()
    bean.filter = IdTokenFilter(idTokenValidator)
    bean.addUrlPatterns("/api/v1/*")
    return bean
  }
}