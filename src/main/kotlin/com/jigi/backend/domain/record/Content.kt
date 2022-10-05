package com.jigi.backend.domain.record

import com.jigi.backend.domain.member.Member
import java.time.LocalDate
import javax.persistence.*

@Entity
class Content(
    val content: String,
    @ManyToOne
    @JoinColumn(name = "writer")
    val writer: Member?,
    @ManyToOne
    @JoinColumn(name = "detail")
    val detail: Detail,
    val date: LocalDate,
){
    @Id @GeneratedValue
    val id: Long = 0
}