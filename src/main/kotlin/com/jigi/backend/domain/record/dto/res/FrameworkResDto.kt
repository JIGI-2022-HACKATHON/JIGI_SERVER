package com.jigi.backend.domain.record.dto.res

import com.jigi.backend.domain.record.Framework

class FrameworkResDto(
    val name: String,
    val id: Long,
){
    constructor(framework: Framework): this(
        id = framework.id,
        name = framework.name,
    )
}