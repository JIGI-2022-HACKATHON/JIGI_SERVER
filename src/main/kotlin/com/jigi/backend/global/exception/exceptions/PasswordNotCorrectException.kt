package com.jigi.backend.global.exception.exceptions

import com.jigi.backend.global.exception.ErrorCode

class PasswordNotCorrectException:BasicException(
    ErrorCode.PASSWORD_NOT_CORRECT
)