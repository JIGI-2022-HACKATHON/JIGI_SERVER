package com.jigi.backend.global.security.handler

import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAccessDeniedHandler : AccessDeniedHandler{
    private val log = LoggerFactory.getLogger(this.javaClass.simpleName)
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        log.error("==========Access Denied==========")
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
    }
}