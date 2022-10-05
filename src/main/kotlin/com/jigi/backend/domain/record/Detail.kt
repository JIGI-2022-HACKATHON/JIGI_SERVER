package com.jigi.backend.domain.record

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Detail(
    val name: String,
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "framework")
    val framework: Framework,
    @JsonIgnore
    @OneToMany(cascade = [CascadeType.REMOVE], mappedBy = "detail")
    val contents: MutableList<Content> = mutableListOf()
){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long=0
    var cnt: Int = 0
}