package com.jigi.backend.global.exception.exceptions

import com.jigi.backend.global.exception.ErrorCode

open class BasicException(
    val errorCode: ErrorCode,
): RuntimeException(
    errorCode.msg
)