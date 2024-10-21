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

    @Transactional
    fun addMember(member: Member): Member{
        return presentationRepository.addMember(member)
    }

    @Transactional
    fun addPetsToMember(memberId : Long, pets: List<Pet>): Member{
        val member = presentationRepository.getMember(memberId)
        pets.forEach { it ->
            member.pets.add(it)
            presentationRepository.addPets(it)
        }

        return presentationRepository.addMember(member)
    }

    @Transactional
    fun updateMember(memberId:Long, member: Member): Member{
        val updatedMember = presentationRepository.getMember(memberId)
        updatedMember.update(member.name, member.age, member.address, member.birthday)
        return presentationRepository.addMember(updatedMember)
    }

    @Transactional
    fun updatePet(memberId:Long, petId: Long, pet: Pet): Member{
        val member = presentationRepository.getMember(memberId)
        val updatedPet = member.pets.get(petId.toInt() - 1)
        updatedPet.update(pet.name, pet.age, pet.breed)
        return presentationRepository.addMember(member)
    }

    @Transactional
    fun deleteMember(memberId: Long){
        presentationRepository.deleteMember(memberId)
    }

    @Transactional
    fun deletePet(petId: Long){
        presentationRepository.deletePet(petId)
    }
}