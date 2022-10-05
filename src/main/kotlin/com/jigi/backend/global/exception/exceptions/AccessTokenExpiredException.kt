package com.jigi.backend.global.exception.exceptions

import com.jigi.backend.global.exception.ErrorCode

class AccessTokenExpiredException: BasicException(
    ErrorCode.TOKEN_EXPIRED
)