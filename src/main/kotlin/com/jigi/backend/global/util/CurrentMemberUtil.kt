package com.jigi.backend.global.util

import com.jigi.backend.domain.member.Member
import com.jigi.backend.domain.member.repository.MemberRepository
import com.jigi.backend.global.security.auth.AuthDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class CurrentMemberUtil(
    private val memberRepository: MemberRepository
){
    private fun getCurrentEmail():String =
        (SecurityContextHolder.getContext().authentication.principal as AuthDetails)
            .getEmail()

    fun getCurrentMember(): Member =
        memberRepository.findByEmail(getCurrentEmail())
            .orElseThrow{RuntimeException()}
}