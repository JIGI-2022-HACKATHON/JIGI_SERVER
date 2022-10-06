package com.jigi.backend.domain.record.service

import com.jigi.backend.domain.member.enums.Field
import com.jigi.backend.domain.member.enums.Grade
import com.jigi.backend.domain.record.Content
import com.jigi.backend.domain.record.Detail
import com.jigi.backend.domain.record.Framework
import com.jigi.backend.domain.record.dto.req.RecordReqDto
import com.jigi.backend.domain.record.dto.res.AllRecordResDto
import com.jigi.backend.domain.record.dto.res.DetailResDto
import com.jigi.backend.domain.record.dto.res.FrameworkResDto
import com.jigi.backend.domain.record.dto.res.TotalRecordResDto
import com.jigi.backend.domain.record.repository.ContentRepository
import com.jigi.backend.domain.record.repository.DetailRepository
import com.jigi.backend.domain.record.repository.FrameworkRepository
import com.jigi.backend.global.exception.exceptions.DetailNotFindException
import com.jigi.backend.global.exception.exceptions.FrameworkNotExistsException
import com.jigi.backend.global.util.CurrentMemberUtil
import org.springframework.stereotype.Service
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
        detail.cnt++
        detail.framework.cnt++
        val content = Content(
            content = recordReqDto.comment,
            writer = currentMemberUtil.getCurrentMember(),
            detail = detail,
            date = LocalDate.now(),
            writtenGrade = currentMemberUtil.getCurrentMember().grade
        )
        contentRepository.save(content)
    }

    fun registerTag(field: Field, framework: String, detail: String){
        if (!frameworkRepository.existsByName(framework)){
            val framework = Framework(
                name = framework,
                field = field,
            )
            frameworkRepository.save(framework)
            if(detailRepository.existsByName(detail)){
                val detail = Detail(
                    name = detail,
                    framework = framework
                )
                detailRepository.save(detail)
            }
        }
        if (!detailRepository.existsByName(detail)){
            val framework = frameworkRepository.findByName(framework).orElseThrow { FrameworkNotExistsException() }
            val detail = Detail(
                name = detail,
                framework = framework
            )
            detailRepository.save(detail)
        }
    }

    fun getTotal(): List<TotalRecordResDto>{
        val list = mutableListOf<TotalRecordResDto>()
        val contents = contentRepository.findAllByDateBetween(LocalDate.now().minusDays(7), LocalDate.now())
        val detailList = mutableListOf<Detail>()
        val frameworkList = mutableListOf<Framework>()
        contents.forEach {
            detailList.add(it.detail)
        }
        detailList.forEach{
            frameworkList.add(it.framework)
        }
        frameworkList.sortBy { it.cnt }
        frameworkList.reverse()
        frameworkList.distinct()
            .forEach{
                it.details.sortBy { it.cnt }
                it.details.reverse()
                if(it.details.size>5){
                    val range = it.details.size - 1
                    for(i:Int in 5..range){
                        it.details.removeAt(5)
                    }
                }
                list.add(
                    TotalRecordResDto(
                        frameworkCount = it.cnt,
                        framework = it.name,
                        details = it.details
                    )
                )
            }
        if(list.size>3){
            val range = list.size - 1
            for(i:Int in 3..range){
                list.removeAt(3)
            }
        }
        return list
    }

    fun getTotalByGradeAndField(grade: Grade, field: Field): List<TotalRecordResDto>{
        val contents = contentRepository.findAllByDateBetween(LocalDate.now().minusDays(7), LocalDate.now())
        val list = mutableListOf<TotalRecordResDto>()
        val detailList = mutableListOf<Detail>()
        val frameworkList = mutableListOf<Framework>()
        contents.forEach {
            if(it.writer?.grade == grade){
                detailList.add(it.detail)
            }
        }
        detailList.distinct()
            .forEach{
                frameworkList.add(it.framework)
            }
        frameworkList.sortBy { it.cnt }
        frameworkList.reverse()
        frameworkList.distinct()
            .forEach{
                if(it.field == field){
                    it.details.sortBy { it.cnt }
                    it.details.reverse()
                    if(it.details.size>5){
                        val range = it.details.size - 1
                        for(i:Int in 5..range){
                            it.details.removeAt(5)
                        }
                    }
                    list.add(
                        TotalRecordResDto(
                            frameworkCount = it.cnt,
                            framework = it.name,
                            details = it.details
                        )
                    )
                }
            }
        if(list.size>3){
            val range = list.size - 1
            for(i:Int in 3..range){
                list.removeAt(3)
            }
        }
        return list
    }

    fun getAllByYearMonthField(year: Int, month: Int, field: Field): List<TotalRecordResDto>{
        val contents = contentRepository.findAll()
        val list = mutableListOf<TotalRecordResDto>()
        val detailList = mutableListOf<Detail>()
        val frameworkList = mutableListOf<Framework>()
        contents.forEach {
            if(it.date.year == year && it.date.month.value == month){
                detailList.add(it.detail)
            }
        }
        detailList.distinct()
            .forEach{
                frameworkList.add(it.framework)
            }
        frameworkList.sortBy { it.cnt }
        frameworkList.reverse()
        frameworkList.distinct()
            .forEach{
                if(it.field == field){
                    it.details.sortBy { it.cnt }
                    it.details.reverse()
                    list.add(
                        TotalRecordResDto(
                            frameworkCount = it.cnt,
                            framework = it.name,
                            details = it.details
                        )
                    )
                }
            }
        return list
    }

    fun getContentByDetail(detailId: Long): List<AllRecordResDto>{
        val detail = detailRepository.findById(detailId).orElseThrow { DetailNotFindException() }
        val contents = detail.contents
        val list = mutableListOf<AllRecordResDto>()
        contents.forEach {
            list.add(
                AllRecordResDto(
                    writerId = it.writer!!.id,
                    writerName = it.writer.name,
                    email = it.writer.email,
                    commentId = it.id,
                    comment = it.content,
                    detailId = it.detail.id,
                    detailName = it.detail.name,
                    frameworkId = it.detail.framework.id,
                    frameworkName = it.detail.framework.name,
                    date = it.date,
                )
            )
        }
        return list
    }

    fun getMyRecord(grade: Grade): List<AllRecordResDto>{
        val list = mutableListOf<AllRecordResDto>()
        val contents = contentRepository.findAllByWriter(currentMemberUtil.getCurrentMember())
        contents.forEach {
            if(it.writtenGrade == grade){
                list.add(
                    AllRecordResDto(
                        writerId = it.writer!!.id,
                        writerName = it.writer.name,
                        email = it.writer.email,
                        commentId = it.id,
                        comment = it.content,
                        detailId = it.detail.id,
                        detailName = it.detail.name,
                        frameworkId = it.detail.framework.id,
                        frameworkName = it.detail.framework.name,
                        date = it.date,
                    )
                )
            }
        }
        return list
    }

    fun getFrameworkByField(field: Field): List<FrameworkResDto> {
        val frameworkList = frameworkRepository.findAllByFieldOrderByCntDesc(field)
        return frameworkList.map { FrameworkResDto(it) }.toList()
    }

    fun getDetailByFramework(frameworkId: Long): List<DetailResDto>{
        val framework = frameworkRepository.findById(frameworkId).orElseThrow { FrameworkNotExistsException() }
        val detailList = detailRepository.findAllByFrameworkOrderByCntDesc(framework)
        return detailList.map { DetailResDto(it) }.toList()
    }
}