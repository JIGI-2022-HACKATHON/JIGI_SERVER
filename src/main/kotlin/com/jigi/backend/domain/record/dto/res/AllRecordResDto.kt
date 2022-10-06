package com.jigi.backend.domain.record.dto.res

import com.jigi.backend.domain.record.Framework
import java.time.LocalDate

class AllRecordResDto(
    val writerId: Long,
    val email:String,
    val writerName: String,
    val frameworkName: String,
    val frameworkId: Long,
    val detailName: String,
    val detailId: Long,
    val commentId: Long,
    val comment: String,
    val date: LocalDate,
) {
}