package com.jigi.backend.domain.member.dto.res

import com.jigi.backend.domain.record.dto.res.AllRecordResDto

class MyPageInfoResDto(
    val memberId: Long,
    val name: String,
    val email: String,
    val records: List<AllRecordResDto>
)