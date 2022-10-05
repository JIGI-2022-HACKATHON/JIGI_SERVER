package com.jigi.backend.domain.record.repository

import com.jigi.backend.domain.record.Content
import org.springframework.data.jpa.repository.JpaRepository

interface ContentRepository : JpaRepository<Content, Long> {
}