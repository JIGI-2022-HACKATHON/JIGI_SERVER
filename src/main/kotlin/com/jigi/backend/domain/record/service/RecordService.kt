package com.jigi.backend.domain.record.service

import com.jigi.backend.domain.record.Content
import com.jigi.backend.domain.record.dto.RecordReqDto
import com.jigi.backend.domain.record.repository.ContentRepository
import com.jigi.backend.domain.record.repository.DetailRepository
import com.jigi.backend.domain.record.repository.FrameworkRepository
import com.jigi.backend.global.exception.exceptions.DetailNotFindException
import com.jigi.backend.global.util.CurrentMemberUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class RecordService(
    val detailRepository: DetailRepository,
    val frameworkRepository: FrameworkRepository,
    val contentRepository: ContentRepository,
    val currentMemberUtil: CurrentMemberUtil,
){

    fun write(recordReqDto: RecordReqDto, detailId: Long){
        val detail = detailRepository.findById(detailId).orElseThrow { DetailNotFindException() }
        val content = Content(
            content = recordReqDto.comment,
            writer = currentMemberUtil.getCurrentMember(),
            detail = detail,
            date = LocalDate.now(),
        )
        println("test")
        contentRepository.save(content)
    }
}