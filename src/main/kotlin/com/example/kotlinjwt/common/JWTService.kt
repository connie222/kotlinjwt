package com.example.kotlinjwt.common

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*

@Component
class JWTService(@Value("\${jwt.secret}") val secret: String,
                 @Value("\${jwt.refresh}") val refresh: String) {

    private val FOUR_HOURS: Long = 1000 * 60 * 60 * 4
    private val ONE_DAYS: Long = 1000 * 60 * 60 * 24

    fun accessToken(username: String, roles: Array<String>): String {
        return generate(username, FOUR_HOURS, roles, secret)
    }

    private fun generate(username: String, expirationInMillis: Long, roles: Array<String>, signature: String): String {
        val now = Date()
        val validity = Date(getExpiration(expirationInMillis))
        return JWT.create()
            .withSubject(username)
            .withIssuer("connie")
            .withExpiresAt(Date(System.currentTimeMillis() + expirationInMillis))
            .withArrayClaim("roles", roles)
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .sign(Algorithm.HMAC512(signature.toByteArray()))
    }
    private fun getExpiration(expirationInMillis: Long): Long = Date().toInstant()
        .plus(Duration.ofMillis(expirationInMillis))
        .toEpochMilli()

}