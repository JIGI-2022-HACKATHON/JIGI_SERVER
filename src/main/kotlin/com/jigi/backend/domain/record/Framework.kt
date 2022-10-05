package com.jigi.backend.domain.record

import javax.persistence.*

@Entity
class Framework(
    val name:String
){
    @Id @GeneratedValue
    val id: Long = 0
    @OneToMany(cascade = [CascadeType.REMOVE], mappedBy = "framework")
    val details: MutableList<Detail> = mutableListOf()
}