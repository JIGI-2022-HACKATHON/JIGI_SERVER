package com.jigi.backend.domain.record.repository

import com.jigi.backend.domain.record.Content
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface ContentRepository : JpaRepository<Content, Long> {
    //select * from content where date>=(curdate()-INTERVAL 7 DAY)
    fun findAllByDateBetween(start: LocalDate, end:LocalDate): List<Content>
}