package com.jigi.backend.global.exception.exceptions

import com.jigi.backend.global.exception.ErrorCode

class UserNotExistsException: BasicException(
    ErrorCode.NOT_EXIST_MEMBER
)