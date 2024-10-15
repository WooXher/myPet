package com.wooxher.portfolio.presentation.service

import com.wooxher.portfolio.domain.entity.Member
import com.wooxher.portfolio.domain.entity.Pet
import com.wooxher.portfolio.presentation.repository.PresentationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
class PresentationService(
    private val presentationRepository: PresentationRepository
) {

    @Transactional(readOnly = true)
    fun getMember(id: Long): Member {
        return presentationRepository.getMember(id)
    }

    @Transactional(readOnly = true)
    fun getPets(id: Long): List<Pet>{
        return presentationRepository.getPets(id)
    }

    fun getMemberPet(memberId:Long, petId:Long): Pet?{
        val member = presentationRepository.getMemberPet(memberId,)
        for (pet in member.pets) {
            if(pet.id == petId) return pet
        }
        return null
    }

}