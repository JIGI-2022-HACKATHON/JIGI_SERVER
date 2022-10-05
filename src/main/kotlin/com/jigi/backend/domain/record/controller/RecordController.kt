package com.jigi.backend.domain.record.controller

import com.jigi.backend.domain.record.dto.RecordReqDto
import com.jigi.backend.domain.record.service.RecordService
import com.jigi.backend.global.response.SuccessResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/record")
class RecordController(
    val recordService: RecordService,
){
    @PostMapping("/{detailId}")
    fun write(@RequestBody recordReqDto: RecordReqDto, @PathVariable detailId: Long): ResponseEntity<SuccessResponse>{
        recordService.write(recordReqDto, detailId)
        return ResponseEntity.ok(SuccessResponse)
    }
}