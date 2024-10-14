package com.wooxher.portfolio.presentation.controller

import com.wooxher.portfolio.domain.entity.Member
import com.wooxher.portfolio.domain.entity.Pet
import com.wooxher.portfolio.presentation.service.PresentationService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PresentationController(
    private val presentationService: PresentationService
) {

    @GetMapping("/v1/member/{member-id}")
    fun getMember(@PathVariable("member-id") id: Long): Member{
        return presentationService.getMember(id)
    }

    @GetMapping("/v1/pets")
    fun getPets(@RequestAttribute("memberId") id: Long): List<Pet>{
        return presentationService.getPets(id)
    }

//    @GetMapping("/v1/pet/{member-id}/{pet-id}")
//    fun getMemberPets(@PathVariable("member-id") memberId: Long,
//                      @PathVariable("pet-id") petId: Long){
//        return presentationService.getMemberPets(memberId, petId)
//    }
}