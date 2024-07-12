package br.com.erudio.data.vo.v2

import jakarta.persistence.*
import java.util.*


data class PersonVO (
    var id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    var adress: String = "",
    var gender: String = "",
    var birthday: Date? = null
)