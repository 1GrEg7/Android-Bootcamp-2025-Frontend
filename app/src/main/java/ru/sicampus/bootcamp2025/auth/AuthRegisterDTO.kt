package ru.sicampus.bootcamp2025.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer


@Serializable
data class AuthRegisterDTO (

    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password:String,
    @SerialName("name")
    val name:String,
    @SerialName("email")
    val email:String = "$username@example.com"

)