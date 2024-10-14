package com.wooxher.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class Pet(
    name: String,
    age: Int,
    breed: String,
    member: Member,
): BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    var id: Long? = null
    var name: String= name
    var age: Int = age
    var breed: String = breed


    fun update(name: String, age: Int, breed: String){
        this.name = name
        this.age = age
        this.breed = breed
    }

}