package com.wooxher.portfolio.presentation.dto

import java.time.LocalDate

data class MemberDTO(
    val name : String,
    val age: Int,
    val address: String,
    val birthday : LocalDate,
) {
}