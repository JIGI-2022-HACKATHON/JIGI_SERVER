package com.jigi.backend.domain.record

import com.jigi.backend.domain.member.Member
import java.time.LocalDate
import javax.persistence.*

@Entity
class Detail(
    val name: String,
    @Column(name = "comment")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Comment", joinColumns = [JoinColumn(name = "detail_id")])
    var comment :MutableSet<String>,
    @ManyToOne
    @JoinColumn(name = "framework")
    val framework: Framework,
    @OneToMany(cascade = [CascadeType.REMOVE], mappedBy = "detail")
    val contents: MutableList<Content> = mutableListOf()
){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long=0
}