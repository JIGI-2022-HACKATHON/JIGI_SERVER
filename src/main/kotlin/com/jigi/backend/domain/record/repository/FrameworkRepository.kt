package com.jigi.backend.domain.record.repository

import com.jigi.backend.domain.member.enums.Field
import com.jigi.backend.domain.record.Framework
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface FrameworkRepository: JpaRepository<Framework, Long> {
    fun findAllByFieldOrderByCntDesc(field: Field): List<Framework>

    fun existsByName(name: String): Boolean

    fun findByName(name: String): Optional<Framework>
}