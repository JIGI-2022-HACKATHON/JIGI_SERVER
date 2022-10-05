package com.jigi.backend.domain.member.repository

import com.jigi.backend.domain.member.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MemberRepository: JpaRepository<Member, Long> {
    fun findByEmail(email: String?): Optional<Member>
}