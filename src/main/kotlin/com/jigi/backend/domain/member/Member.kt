package com.jigi.backend.domain.member

import com.jigi.backend.domain.member.enums.Field
import com.jigi.backend.domain.member.enums.Grade
import javax.persistence.*

@Entity
class Member(
    val email: String,
    val password: String,
    val name: String,
    @Enumerated(EnumType.STRING) @Column(name = "Role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Role", joinColumns = [JoinColumn(name = "member_id")])
    val roles: MutableList<Role>,
    @Enumerated(EnumType.STRING)
    val grade: Grade,
    @Enumerated(EnumType.STRING)
    val field: Field,
) {
    @Id
    @GeneratedValue
    val id: Long = 0

    var refreshToken: String? = null

    fun updateRefreshToken(refreshToken: String?){
        this.refreshToken = refreshToken
    }
}