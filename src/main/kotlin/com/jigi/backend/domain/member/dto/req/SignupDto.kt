package com.jigi.backend.domain.member.dto.req

import com.jigi.backend.domain.member.Member
import com.jigi.backend.domain.member.Role
import com.jigi.backend.domain.member.enums.Field
import com.jigi.backend.domain.member.enums.Grade
import java.util.Collections
import javax.validation.constraints.Pattern

class SignupDto(
    @field:Pattern(regexp = "^[a-zA-Z0-9]+@+gsm.hs.kr|dsm.hs.kr|dgsm.hs.kr|bsm.hs.kr$")
    val email: String,
    val password: String,
    val grade: Grade,
    val name: String,
    val field: Field,
){
    fun toEntity(password: String): Member{
        return Member(
            email = email,
            password = password,
            grade = grade,
            name = name,
            field = field,
            roles = Collections.singletonList(Role.ROLE_MEMBER)
        )
    }
}