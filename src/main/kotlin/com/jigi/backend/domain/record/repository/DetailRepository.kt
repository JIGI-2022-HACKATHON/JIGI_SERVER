package com.jigi.backend.domain.record.repository

import com.jigi.backend.domain.record.Detail
import org.springframework.data.jpa.repository.JpaRepository

interface DetailRepository :JpaRepository<Detail, Long>{
}