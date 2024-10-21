package com.wooxher.portfolio.domain.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

@Entity
class Pet(
    name: String,
    age: Int,
    breed: String,
): BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    var id: Long? = null
    var name: String= name
    var age: Int = age
    var breed: String = breed

//    @ManyToOne(
//        fetch = FetchType.LAZY
//    )
//    @JoinColumn(name = "member_id")
//    var member : Member = member

    fun update(name: String, age: Int, breed: String){
        this.name = name
        this.age = age
        this.breed = breed
    }

}