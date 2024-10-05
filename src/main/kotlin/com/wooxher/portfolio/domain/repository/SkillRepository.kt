package com.wooxher.portfolio.domain.repository

import com.wooxher.portfolio.domain.entity.Skill
import org.springframework.data.jpa.repository.JpaRepository

interface SkillRepository : JpaRepository<Skill, Long>