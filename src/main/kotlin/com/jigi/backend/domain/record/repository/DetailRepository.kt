package com.jigi.backend.domain.record.repository

import com.jigi.backend.domain.record.Detail
import com.jigi.backend.domain.record.Framework
import org.springframework.data.jpa.repository.JpaRepository

interface DetailRepository :JpaRepository<Detail, Long>{
    fun findAllByFrameworkOrderByCntDesc(framework: Framework): List<Detail>
    fun existsByName(name:String): Boolean
}