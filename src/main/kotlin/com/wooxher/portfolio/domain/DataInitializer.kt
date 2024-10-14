package com.wooxher.portfolio.domain

import com.wooxher.portfolio.domain.repository.MemberRepository
import com.wooxher.portfolio.domain.repository.PetRepository
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile(value = ["default"])
class DataInitializer(
    private val memberRepository : MemberRepository,
    private val petRepository: PetRepository,
) {

    @PostConstruct
    fun initData(){
    }
}