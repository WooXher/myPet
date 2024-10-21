package com.wooxher.portfolio.domain.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
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

    @NotNull @NotBlank
    var name: String = name
    @NotNull
    var age: Int = age
    @NotNull @NotBlank
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

    fun addMemberPet(pet: List<Pet>?){
        if(pet!=null){
            this.pets.addAll(pet)
        }
    }
}