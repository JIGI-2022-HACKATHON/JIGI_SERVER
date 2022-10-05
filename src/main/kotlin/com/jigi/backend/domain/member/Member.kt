package com.jigi.backend.domain.member

import javax.persistence.*

@Entity
class Member(
    var email: String,
    var password: String,
    var name: String,
    @Enumerated(EnumType.STRING) @Column(name = "Role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Role", joinColumns = [JoinColumn(name = "member_id")])
    var roles: MutableList<Role>,
) {
    @Id
    @GeneratedValue
    val id: Long = 0

    var refreshToken: String? = null

    fun updateRefreshToken(refreshToken: String?){
        this.refreshToken = refreshToken
    }
}