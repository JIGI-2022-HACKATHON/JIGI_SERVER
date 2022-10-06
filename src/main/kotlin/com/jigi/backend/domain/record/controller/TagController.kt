package com.jigi.backend.domain.record.controller

import com.jigi.backend.domain.member.enums.Field
import com.jigi.backend.domain.record.dto.res.DetailResDto
import com.jigi.backend.domain.record.dto.res.FrameworkResDto
import com.jigi.backend.domain.record.service.RecordService
import com.jigi.backend.global.response.SuccessResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tag")
class TagController (
    val recordService: RecordService,
){
    @PostMapping
    fun registerTag(
        @RequestParam field: Field,
        @RequestParam framework: String,
        @RequestParam detail: String,
    ): ResponseEntity<SuccessResponse>{
        recordService.registerTag(field, framework, detail)
        return ResponseEntity.ok(SuccessResponse)
    }

    @GetMapping("/framework/{field}")
    fun getFramework(@PathVariable field: Field): ResponseEntity<List<FrameworkResDto>> =
        ResponseEntity.ok(recordService.getFrameworkByField(field))

    @GetMapping("/detail/{frameworkId}")
    fun getDetail(@PathVariable frameworkId: Long): ResponseEntity<List<DetailResDto>> =
        ResponseEntity.ok(recordService.getDetailByFramework(frameworkId))
}