package com.wooxher.portfolio.domain.repository

import com.wooxher.portfolio.domain.entity.Pet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface PetRepository: JpaRepository<Pet, Long> {


}