package com.wooxher.portfolio.presentation.service

import com.wooxher.portfolio.domain.entity.Member
import com.wooxher.portfolio.domain.entity.Pet
import com.wooxher.portfolio.presentation.repository.PresentationRepository
import org.springframework.stereotype.Service

@Service
class PresentationService(
    private val presentationRepository: PresentationRepository
) {

    fun getMember(id: Long): Member {
        return presentationRepository.getMember(id)
    }

    fun getPets(id: Long): List<Pet>{
        return presentationRepository.getPets(id)
    }

//    fun getMemberPets(memberId:Long, petId:Long): List<Pet>{
//        return presentationRepository.getMemberPets(memberId, petId)
//    }

}