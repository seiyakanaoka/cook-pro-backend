package com.example.project.presentation.filter

import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
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
    var decodedToken: DecodedJWT? = null
    val header = request?.getHeader("Authorization")

//    TODO: プリフライトリクエストが飛んでくるので、拒否するようにする
    if (request?.method == "OPTIONS") {
      chain?.doFilter(request, response);
      return;
    }

    // Authorizationヘッダーがnullの場合はエラー
    if (header == null) {
      response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ログインしてください")
      return;
    }

    // トークンがなかったらエラー
    val token = header.replaceFirst("Bearer ", "");
    if (token == "") {
      response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ログインしてください")
      return;
    }

    try {
      decodedToken = idTokenValidator.idTokenVerify(token)
    } catch (e: JWTVerificationException) {
      response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.message)
      return;
    }

    // デコードできなかったらエラー
    if (decodedToken == null) {
      response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "トークンがデコードできませんでした")
      return;
    }

    // トークンテーブルにトークンが存在してなかったらエラー
    // TODO: ユーザーが存在するかを確認する処理を追加する
//    if (!tokenRepository.existsByToken(token)) {
//      response?.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ログインしてください。")
//      return;
//    }

    // IDとEメールをリクエスト属性に設定
    request?.setAttribute("userId", decodedToken.subject);
    request?.setAttribute("email", decodedToken.getClaim("email").asString());

    chain?.doFilter(request, response);

  }

  @Bean
  fun filter(): FilterRegistrationBean<IdTokenFilter>? {
    val bean: FilterRegistrationBean<IdTokenFilter> = FilterRegistrationBean<IdTokenFilter>()
    bean.filter = IdTokenFilter(idTokenValidator)
    bean.addUrlPatterns("/api/v1/*")
    bean.order = 1
    return bean
  }
}