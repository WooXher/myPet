package com.wooxher.portfolio.presentation.repository

import com.wooxher.portfolio.domain.entity.Member
import com.wooxher.portfolio.domain.entity.Pet
import com.wooxher.portfolio.domain.repository.MemberRepository
import com.wooxher.portfolio.domain.repository.PetRepository
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class PresentationRepository(
    private val memberRepository: MemberRepository,
    private val petRepository: PetRepository
) {

    fun getMember(id:Long): Member = memberRepository.findById(id).orElseThrow()

    fun getPets(id:Long): List<Pet> {
        val member = memberRepository.findById(id).orElseThrow()
        return member.pets
    }
//
//    fun getMemberPets(memberId:Long, petId:Long):List<Pet>{
//        val member = memberRepository.findById(memberId).orElseThrow()
//        petRepository.findById()
//    }
}