package ru.sicampus.bootcamp2025.data.centersData

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CenterDTO (
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("location")
    val location: String,

    )