package com.jigi.backend.global.security.auth

import com.jigi.backend.domain.member.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class AuthDetailService(
    private val memberRepository: MemberRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails =
        AuthDetails(memberRepository.findByEmail(username)
            .orElseThrow{RuntimeException()})
}