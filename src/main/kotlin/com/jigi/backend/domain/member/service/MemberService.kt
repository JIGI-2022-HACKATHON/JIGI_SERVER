package com.jigi.backend.domain.member.service

import com.jigi.backend.domain.member.dto.req.SigninDto
import com.jigi.backend.domain.member.dto.req.SignupDto
import com.jigi.backend.domain.member.dto.res.SigninResDto
import com.jigi.backend.domain.member.repository.MemberRepository
import com.jigi.backend.global.exception.exceptions.MemberAlreadyExistException
import com.jigi.backend.global.exception.exceptions.PasswordNotCorrectException
import com.jigi.backend.global.exception.exceptions.UserNotExistsException
import com.jigi.backend.global.security.jwt.TokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider,
){
    fun signup(signupDto: SignupDto): Long{
        if (memberRepository.existsByEmail(signupDto.email))
            throw MemberAlreadyExistException()
        val member = signupDto.toEntity(passwordEncoder.encode(signupDto.email))
        return memberRepository.save(member).id
    }

    fun siginin(signinDto: SigninDto): SigninResDto{
        if(!memberRepository.existsByEmail(signinDto.email))
            throw UserNotExistsException()
        val member = memberRepository.findByEmail(signinDto.email).orElseThrow { throw UserNotExistsException() }
        if(passwordEncoder.matches(signinDto.password, member.password))
            throw PasswordNotCorrectException()
        val accessToken = tokenProvider.createAccessToken(member.email, member.roles)
        return SigninResDto(
            accessToken = accessToken
        )
    }
}