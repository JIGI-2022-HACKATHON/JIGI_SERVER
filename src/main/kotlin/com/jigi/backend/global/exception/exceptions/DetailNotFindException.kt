package com.jigi.backend.global.exception.exceptions

import com.jigi.backend.global.exception.ErrorCode

class DetailNotFindException :BasicException(
    ErrorCode.DETAIL_NOT_FIND
)