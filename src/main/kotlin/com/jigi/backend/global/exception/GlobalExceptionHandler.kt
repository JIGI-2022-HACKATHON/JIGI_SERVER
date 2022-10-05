package com.jigi.backend.global.exception

import com.jigi.backend.global.exception.exceptions.BasicException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)

    @ExceptionHandler(BasicException::class)
    fun printError(request: HttpServletRequest, ex: BasicException): ResponseEntity<ErrorResponse> {
        log.error(request.requestURI)
        log.error(ex.message)
        ex.printStackTrace()
        val errorResponse = ErrorResponse(ex.errorCode.msg, ex.errorCode.code)
        return ResponseEntity(errorResponse, HttpStatus.valueOf(ex.errorCode.code))
    }

}