package com.wooxher.portfolio.domain.repository

import com.wooxher.portfolio.domain.entity.Member
import com.wooxher.portfolio.domain.entity.Pet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*

interface MemberRepository: JpaRepository<Member, Long> {

    @Query("select m from Member m left join fetch m.pets where m.id = :id")
    override fun findById(id: Long): Optional<Member>

    @Query("select m from Member m left join fetch m.pets where m.id = :memberId")
    fun findByMemberIdAndPetId(@Param("memberId") memberId:Long,):Optional<Member>
}