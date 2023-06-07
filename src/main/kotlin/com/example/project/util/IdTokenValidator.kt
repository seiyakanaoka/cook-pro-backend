package com.example.project.util


import com.auth0.jwk.GuavaCachedJwkProvider
import com.auth0.jwk.Jwk
import com.auth0.jwk.JwkException
import com.auth0.jwk.UrlJwkProvider
import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.example.project.exception.InvalidTokenException
import java.net.MalformedURLException
import java.net.URL
import java.security.interfaces.RSAPublicKey
import java.util.*

/**
 * Cognitoで認証して得られるid tokenを扱う
 */
class IdTokenValidator {
  /**
   * IDトークン を検証する
   *
   * @param idToken 検証対象のIDトークン
   * @return 検証に成功した場合は ID Tokenのペイロード
   *
   * @throws InvalidTokenException ID Tokenの値が不正なので認証に失敗した
   */
  @Throws(InvalidTokenException::class)
  fun verify(idToken: String?): DecodedJWT? {
    val decodedToken: DecodedJWT = JWT.decode(idToken)

    // cognitoのユーザプールで署名された事を確認する
    val iss: String = decodedToken.issuer
    if (!iss.contains("https://cognito-idp")) {
      throw InvalidTokenException(500, "ID トークンの発行者が対象のシステムではありません。iss=$iss idToken=$idToken")
    }

    //  ID トークンの用途が「ID」であることを確認します。
    val tokenUse: String = decodedToken.getClaim("token_use").asString()
    if ("id" != tokenUse) {
      throw InvalidTokenException(500, "ID トークンの用途が ID ではありません。token_use=$tokenUse idToken=$idToken")
    }

    // 署名のアルゴリズムを確認します。
    val alg: String = decodedToken.algorithm
    if ("RS256" != decodedToken.algorithm) {
      throw InvalidTokenException(500, "ID トークンの署名アルゴリズムが対応していないものです。alg =$alg idToken=$idToken")
    }

    // payloadと署名を検証します。
    var decodedJWT: DecodedJWT? = null
    if (tokenVerify(decodedToken).also { decodedJWT = it } == null) {
      throw InvalidTokenException(500, "ID Tokenの検証に失敗しました。")
    }
    return decodedJWT
  }

  /**
   * auth0の のライブラリを利用して 検証を行う
   *
   * @param kid ID トークンのヘッダーにある キーID
   * @return nullでなければ デコードされた ID トークン
   *
   * @throws InvalidTokenException 検証に失敗した
   */
  @Throws(InvalidTokenException::class)
  private fun tokenVerify(jwToken: DecodedJWT): DecodedJWT? {
    return try {
      getVerifier(jwToken)?.verify(jwToken)

    } catch (e: Exception) {
      throw InvalidTokenException(500, e.message)
    }
  }

  /**
   * JWTVerifier のインスタンスを取得する。
   * JWTVerifier は ver.3 からスレッドセーフになったので再利用する。
   * ただし、署名に使われた RSAキーペアが更新される可能性を考えて、定期的に更新する
   * @param kid 署名に使われたキーID
   *
   * @return
   *
   * @throws MalformedURLException
   * @throws JwkException
   */
  @Throws(MalformedURLException::class, JwkException::class)
  private fun getVerifier(decodedToken: DecodedJWT): JWTVerifier? {
    if (decodedToken.expiresAt.time < Date().time) {
      throw InvalidTokenException(500, "有効期限が切れています")
    }
    val http = UrlJwkProvider(URL(jwksUrl(decodedToken.issuer)))
    val provider = GuavaCachedJwkProvider(http)
    val jwk: Jwk = provider.get(decodedToken.keyId)
    val algorithm: Algorithm = Algorithm.RSA256(jwk.publicKey as RSAPublicKey, null)
    return JWT.require(algorithm)
      .withIssuer(decodedToken.issuer)
      .build()
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