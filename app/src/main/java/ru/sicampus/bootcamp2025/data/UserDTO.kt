package ru.sicampus.bootcamp2025.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserDTO (
    @SerialName("username") //можно потом поменять если что
    val username: String,
    @SerialName("emain")
    val email: String,
    @SerialName("phoneNumber")
    val phoneNumber: String

)