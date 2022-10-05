package com.jigi.backend.domain.record.service

import com.jigi.backend.domain.member.enums.Field
import com.jigi.backend.domain.record.Content
import com.jigi.backend.domain.record.Detail
import com.jigi.backend.domain.record.Framework
import com.jigi.backend.domain.record.dto.req.RecordReqDto
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
        detailList.distinct()
        detailList.forEach{
            frameworkList.add(it.framework)
        }
        frameworkList.distinct()
            .forEach{
                list.add(
                    TotalRecordResDto(
                        frameworkCount = it.cnt,
                        framework = it.name,
                        details = it.details
                    )
                )
            }
        return list
    }

    fun getTotalByField(field: Field): List<TotalRecordResDto>{
        val frameworkList = frameworkRepository.findAllByFieldOrderByCntDesc(field)
        val list = mutableListOf<TotalRecordResDto>()
        val loop = (if (frameworkList.size <= 3) frameworkList.size else 3) - 1
        for(i : Int in 0..loop){
            val framework = frameworkList.get(i)
            val detailList = mutableListOf<Detail>()
            val details = detailRepository.findAllByFrameworkOrderByCntDesc(framework)
            val loop1 = (if (details.size <= 5) details.size else 5) - 1
            for(i : Int in 0..loop1){
                val detail = details.get(i)
                detailList.add(detail)
            }
            list.add(
                TotalRecordResDto(
                    framework = framework.name,
                    details = detailList,
                    frameworkCount = framework.cnt
                )
            )
        }
        return list
    }

    fun getTotalByGrade(){

    }
}