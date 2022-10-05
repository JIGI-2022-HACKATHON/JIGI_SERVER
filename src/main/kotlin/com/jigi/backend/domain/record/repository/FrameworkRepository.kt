package com.jigi.backend.domain.record.repository

import com.jigi.backend.domain.record.Framework
import org.springframework.data.jpa.repository.JpaRepository

interface FrameworkRepository: JpaRepository<Framework, Long> {
}