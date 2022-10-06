package com.jigi.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.PostMapping
import java.time.LocalDateTime
import java.util.*

@SpringBootApplication
class BackendApplication

fun main(args: Array<String>) {
	TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
	runApplication<BackendApplication>(*args)
}
