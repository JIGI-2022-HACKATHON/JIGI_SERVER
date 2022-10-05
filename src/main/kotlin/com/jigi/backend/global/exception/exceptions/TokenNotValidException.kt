package com.jigi.backend.global.exception.exceptions

import com.jigi.backend.global.exception.ErrorCode

class TokenNotValidException: BasicException(
    ErrorCode.TOKEN_NOT_VALID
)