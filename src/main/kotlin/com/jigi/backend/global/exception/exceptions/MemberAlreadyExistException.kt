package com.jigi.backend.global.exception.exceptions

import com.jigi.backend.global.exception.ErrorCode

class MemberAlreadyExistException: BasicException(
    ErrorCode.USER_ALREADY_EXIST
)