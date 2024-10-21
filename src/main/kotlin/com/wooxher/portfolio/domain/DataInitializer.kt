package com.wooxher.portfolio.domain

import com.wooxher.portfolio.domain.entity.Member
import com.wooxher.portfolio.domain.entity.Pet
import com.wooxher.portfolio.domain.repository.MemberRepository
import com.wooxher.portfolio.domain.repository.PetRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DataInitializer (
    private val memberRepository: MemberRepository,
    private val petRepository: PetRepository,
){
    @PostConstruct
    fun initialize(){
        val member1: Member =
            Member(name = "멤버1", age = 21, address = "서울시 서울구 서울동", birthday = LocalDate.of(2000,1,1))
        member1.addMemberPet(
            mutableListOf(
                Pet(name = "펫1", age = 1, breed = "푸들",)
            )
        )

        val member2: Member =
            Member(name = "멤버2", age = 22, address = "서울시 서울구 서울동", birthday = LocalDate.of(2000,2,2))
        member2.addMemberPet(
            mutableListOf(
                Pet(name = "펫2", age = 2, breed = "푸들",)
            )
        )

        memberRepository.saveAll(mutableListOf(member1, member2))
    }




}