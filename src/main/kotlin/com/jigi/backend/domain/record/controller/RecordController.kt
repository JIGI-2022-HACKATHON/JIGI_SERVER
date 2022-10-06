package com.jigi.backend.domain.record.controller

import com.jigi.backend.domain.member.enums.Field
import com.jigi.backend.domain.member.enums.Grade
import com.jigi.backend.domain.record.dto.req.RecordReqDto
import com.jigi.backend.domain.record.dto.res.AllRecordResDto
import com.jigi.backend.domain.record.dto.res.TotalRecordResDto
import com.jigi.backend.domain.record.service.RecordService
import com.jigi.backend.global.response.SuccessResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
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

    @GetMapping("/main")
    fun getMain():ResponseEntity<List<TotalRecordResDto>>{
        val total = recordService.getTotal()
        return ResponseEntity.ok(total)
    }

    @GetMapping("/main/{grade}/{field}")
    fun getMain(@PathVariable grade: Grade, @PathVariable field: Field):ResponseEntity<List<TotalRecordResDto>>{
        val total = recordService.getTotalByGradeAndField(grade, field)
        return ResponseEntity.ok(total)
    }

    @GetMapping("/all/{year}/{month}/{field}")
    fun getAllByField(@PathVariable year: Int, @PathVariable month: Int, @PathVariable field: Field):ResponseEntity<List<TotalRecordResDto>>{
        val all = recordService.getAllByYearMonthField(year, month, field)
        return ResponseEntity.ok(all)
    }

    @GetMapping("/all/detail/{detailId}")
    fun getAllByDetailId(@PathVariable detailId: Long): ResponseEntity<List<AllRecordResDto>>{
        val list = recordService.getContentByDetail(detailId)
        return ResponseEntity.ok(list)
    }
}