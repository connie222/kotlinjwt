package com.example.kotlinjwt.auth.service.impl

import com.example.kotlinjwt.adapter.KakaoAuth
import com.example.kotlinjwt.auth.service.AuthService
import com.example.kotlinjwt.enum.SnsType
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class AuthServiceImpl(
    val kakaoAuth: KakaoAuth
): AuthService {
    override fun getAccessToken(memberId: Long): TokenRes {
        TODO("Not yet implemented")
    }

    override fun verifyAccessToken(accessToken: String, snsType: SnsType, snsId: String): Boolean =
       when (snsType) {
           SnsType.EMAIL -> true
           SnsType.KAKAO -> kakaoAuth.verifyAccessToken(accessToken, snsId)
           else -> throw Exception()
       //throw SigninSnsException(ApplicationErrorMessageCode.SNSTYPE_IS_MISMATCHED)
       }
}


data class TokenRes(
    val accessToken: String,
    val username: String,
    var certCount: Int? =null
)
