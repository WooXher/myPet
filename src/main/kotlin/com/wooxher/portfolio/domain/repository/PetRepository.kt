package com.wooxher.portfolio.domain.repository

import com.wooxher.portfolio.domain.entity.Pet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface PetRepository: JpaRepository<Pet, Long> {

//    @Query("select p from Pet p ")
//    fun findByIdAndMemberId(id:Long, memberId:Long):Optional<Pet>
}