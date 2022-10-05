package com.jigi.backend.domain.record.dto.res

import com.jigi.backend.domain.record.Detail

class TotalRecordResDto(
    val framework: String,
    val frameworkCount: Int,
    val details: List<Detail>
)