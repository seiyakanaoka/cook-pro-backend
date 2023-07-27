package com.example.project.util


import com.auth0.jwk.GuavaCachedJwkProvider
import com.auth0.jwk.Jwk
import com.auth0.jwk.JwkException
import com.auth0.jwk.UrlJwkProvider
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.AlgorithmMismatchException
import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.TokenExpiredException
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.stereotype.Component
import java.net.MalformedURLException
import java.net.URL
import java.security.interfaces.RSAPublicKey
import java.util.*

/**
 * Cognitoで認証して得られるid tokenを扱う
 */
@Component
class IdTokenValidator {
  private var verifier: JWTVerifier? = null

  private val jwt = JWT()

  /**
   * IDトークン を検証する
   *
   * @param idToken 検証対象のIDトークン
   * @return 検証に成功した場合は ID Tokenのペイロード
   *
   */
  fun idTokenVerify(idToken: String): DecodedJWT? {
    val decodedToken: DecodedJWT = jwt.decodeJwt(idToken)

    // cognitoのユーザプールで署名された事を確認する
    val iss: String = decodedToken.issuer
    if (!iss.contains("https://cognito-idp")) {
      throw JWTDecodeException("ID トークンの発行者が対象のシステムではありません iss=$iss idToken=$idToken")
    }

    //  ID トークンの用途が「ID」であることを確認する
    val tokenUse: String = decodedToken.getClaim("token_use").asString()
    if ("id" != tokenUse) {
      throw JWTDecodeException("IDトークンの用途がIDではありません token_use=$tokenUse idToken=$idToken")
    }

    // 署名のアルゴリズムを確認する
    val alg: String = decodedToken.algorithm
    if ("RS256" != decodedToken.algorithm) {
      throw AlgorithmMismatchException("IDトークンの署名アルゴリズムが対応していないものです alg =$alg idToken=$idToken")
    }

    // jwtを検証する
    return tokenVerify(decodedToken)
  }

  /**
   * auth0の のライブラリを利用して 検証を行う
   *
   * @param kid ID トークンのヘッダーにある キーID
   * @return nullでなければ デコードされた ID トークン
   */
  private fun tokenVerify(decodedJWT: DecodedJWT): DecodedJWT? {
    return getVerifier(decodedJWT)?.verify(decodedJWT)
  }

  /**
   * JWTVerifier のインスタンスを取得する。
   * ただし、署名に使われた RSAキーペアが更新される可能性を考えて、定期的に更新する
   * @param kid 署名に使われたキーID
   *
   * @return
   *
   * @throws MalformedURLException
   * @throws JwkException
   */
  private fun getVerifier(decodedToken: DecodedJWT): JWTVerifier? {
    if (decodedToken.expiresAt.time < Date().time) {
      throw TokenExpiredException("有効期限が切れています")
    }

    // synchronizedで複数スレッドが実行した場合、同期処理として扱うようにしてスレッドセーフにする
    synchronized(jwt) {
      if (decodedToken.expiresAt.time < Date().time) {
        throw TokenExpiredException("有効期限が切れています")
      }
      val http = UrlJwkProvider(URL(jwksUrl(decodedToken.issuer)))
      val provider = GuavaCachedJwkProvider(http)
      val jwk: Jwk = provider.get(decodedToken.keyId)
      val algorithm: Algorithm = Algorithm.RSA256(jwk.publicKey as RSAPublicKey, null)
      verifier = JWT.require(algorithm)
        .withIssuer(decodedToken.issuer)
        .build()
    }

    return verifier
  }

  /**
   * JSON Web トークン (JWT) セットのURLを取得します。
   *
   * @return
   */
  private fun jwksUrl(issuer: String): String {
    return "$issuer/.well-known/jwks.json"
  }
}