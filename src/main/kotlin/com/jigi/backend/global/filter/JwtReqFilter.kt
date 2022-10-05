package com.jigi.backend.global.filter

import com.jigi.backend.global.exception.ErrorCode
import com.jigi.backend.global.exception.exceptions.AccessTokenExpiredException
import com.jigi.backend.global.exception.exceptions.TokenNotValidException
import com.jigi.backend.global.security.jwt.TokenProvider
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtReqFilter(
    val tokenProvider: TokenProvider,
    val registerUserInfo: RegisterUserInfo,
): OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val accessToken = request.getHeader("Authorization")
        if(accessToken!=null){
            if(tokenProvider.isTokenExpired(accessToken)){
                throw AccessTokenExpiredException()//토큰만료
            }else if(!tokenProvider.getTokenType(accessToken).equals("accessToken")){
                throw TokenNotValidException()
            }
            registerUserInfo.registerSecurityContext(request, accessToken)
        }
        filterChain.doFilter(request, response)
    }
}