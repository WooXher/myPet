package com.wooxher.portfolio.domain.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Member(
    name: String,
    age: Int,
    address: String,
    birthday: LocalDate,
) : BaseEntity(){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var Id : Long? = null

    var name: String = name
    var age: Int = age
    var address: String = address
    var birthday: LocalDate = birthday

    @OneToMany(
        targetEntity = Pet::class,
        fetch = FetchType.LAZY,
        cascade =[CascadeType.ALL],
        )
    @JoinColumn(name = "member_id",)
    var pets:MutableList<Pet> = mutableListOf()

    fun update(name: String, age: Int, address: String, birthday: LocalDate,){
        this.name = name
        this.age = age
        this.address = address
        this.birthday = birthday
    }

    fun addMemberPet(pet: MutableList<Pet>?){
        if(pet!=null){
            this.pets.addAll(pet)
        }
    }

}