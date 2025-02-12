package ru.sicampus.bootcamp2025.domain.centersInfo

import ru.sicampus.bootcamp2025.data.centersData.CenterDTO
import ru.sicampus.bootcamp2025.data.userData.UserDTO
import ru.sicampus.bootcamp2025.ui.screens.Center

interface CenterRepo {
    suspend fun fetchAllCenters(): List<CenterDTO>
}