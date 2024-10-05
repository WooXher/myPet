package com.wooxher.portfolio.domain.repository

import com.wooxher.portfolio.domain.entity.Link
import org.springframework.data.jpa.repository.JpaRepository

interface LinkRepository : JpaRepository<Link, Long>