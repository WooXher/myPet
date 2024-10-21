package com.wooxher.portfolio.presentation.controller

import com.wooxher.portfolio.domain.entity.Member
import com.wooxher.portfolio.domain.entity.Pet
import com.wooxher.portfolio.presentation.service.PresentationService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PresentationController(
    private val presentationService: PresentationService
) {

    @GetMapping("/v1/member/{member-id}")
    fun getMember(@PathVariable("member-id") id: Long): Member{
        return presentationService.getMember(id)
    }

    @GetMapping("/v1/pets/{member-id}")
    fun getPets(@PathVariable("member-id") id: Long): List<Pet>{
        return presentationService.getPets(id)
    }

    @GetMapping("/v1/pet/{member-id}/{pet-id}")
    fun getMemberPet(@PathVariable("member-id") memberId: Long,
                      @PathVariable("pet-id") petId: Long
        ): Pet?{
        return presentationService.getMemberPet(memberId, petId)
    }

    @PostMapping("v1/member")
    fun addMember(@RequestBody member: Member
        ): Member{
        return presentationService.addMember(member)
    }

    @PostMapping("/v1/pets/{member-id}")
    fun addPet(@PathVariable("member-id") memberId : Long,
               @RequestBody pets: List<Pet>,
        ): Member{
        return presentationService.addPetsToMember(memberId, pets)
    }

    @PutMapping("/v1/member/{member-id}")
    fun updateMember(@PathVariable("member-id",) memberId:Long,
                     @RequestBody member: Member,
         ): Member{
        return presentationService.updateMember(memberId, member)
    }

    @PutMapping("/v1/pet/{member-id}/{pet-id}")
    fun updatePet(@PathVariable("member-id",) memberId:Long,
                  @PathVariable("pet-id",) petId:Long,
                  @RequestBody pet: Pet,): Member{
        return presentationService.updatePet(memberId, petId, pet)
    }

    @DeleteMapping("/v1/member/{member-id}")
    fun deleteMember(@PathVariable("member-id") memberId: Long){
        presentationService.deleteMember(memberId)
    }

    @DeleteMapping("/v1/pet/{member-id}/{pet-id}")
    fun deletePet(@PathVariable("pet-id") petId: Long){
        presentationService.deletePet(petId)
    }
}