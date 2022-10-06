package com.jigi.backend.domain.record.dto.res

import com.jigi.backend.domain.record.Detail
import java.util.StringJoiner

class DetailResDto(
    val name: String,
    val id: Long,
){
    constructor(detail: Detail):this(
        id = detail.id,
        name = detail.name
    )
}