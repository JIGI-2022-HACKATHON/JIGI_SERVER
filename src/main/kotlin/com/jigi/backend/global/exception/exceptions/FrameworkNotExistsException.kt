package com.jigi.backend.global.exception.exceptions

import com.jigi.backend.global.exception.ErrorCode

class FrameworkNotExistsException: BasicException(
    ErrorCode.FRAMEWORK_NOT_FIND
)