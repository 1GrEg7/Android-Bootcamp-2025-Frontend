package ru.sicampus.bootcamp2025.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserDTO (
    @SerialName("firstName")
    val id: Int,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("secondName")
    val secondName: String,
    @SerialName("age")
    val age: Int,
    @SerialName("email")
    val email: String,
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("status")
    val status: String,
    @SerialName("experience")
    val experience: String,
    @SerialName("description")
    val description:String,
    @SerialName("volunteerCenter")
    val volunteerCenter:String,


)