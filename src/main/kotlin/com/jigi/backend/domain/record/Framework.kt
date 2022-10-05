package com.jigi.backend.domain.record

import com.jigi.backend.domain.member.enums.Field
import javax.persistence.*

@Entity
class Framework(
    val name:String,
    val field:Field,
){
    @Id @GeneratedValue
    val id: Long = 0
    @OneToMany(cascade = [CascadeType.REMOVE], mappedBy = "framework")
    val details: MutableList<Detail> = mutableListOf()
    var cnt: Int = 0
}