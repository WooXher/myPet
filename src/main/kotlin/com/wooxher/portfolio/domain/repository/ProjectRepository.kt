package com.wooxher.portfolio.domain.repository

import com.wooxher.portfolio.domain.entity.Project
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectRepository : JpaRepository<Project, Long>