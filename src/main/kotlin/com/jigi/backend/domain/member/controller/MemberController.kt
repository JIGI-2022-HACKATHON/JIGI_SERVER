package com.jigi.backend.domain.member.controller

import com.jigi.backend.domain.member.dto.req.SigninDto
import com.jigi.backend.domain.member.dto.req.SignupDto
import com.jigi.backend.domain.member.dto.res.MyPageInfoResDto
import com.jigi.backend.domain.member.dto.res.SigninResDto
import com.jigi.backend.domain.member.enums.Grade
import com.jigi.backend.domain.member.service.MemberService
import com.jigi.backend.domain.record.service.RecordService
import com.jigi.backend.global.response.SuccessResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class MemberController (
    val memberService: MemberService,
){
    @PostMapping("/join")
    fun signup(@RequestBody @Valid signupDto: SignupDto): ResponseEntity<SuccessResponse>{
        memberService.signup(signupDto)
        return ResponseEntity.ok(SuccessResponse)
    }

    @PostMapping("/login")
    fun signin(@RequestBody signinDto: SigninDto): ResponseEntity<SigninResDto> =
        ResponseEntity.ok(memberService.siginin(signinDto))

    @GetMapping("/my/{grade}")
    fun getMyInfo(@PathVariable grade: Grade): ResponseEntity<MyPageInfoResDto> =
        ResponseEntity.ok(memberService.getMyPageInfo(grade))
}