package ru.sicampus.bootcamp2025.domain.centersInfo

import ru.sicampus.bootcamp2025.data.centersData.CenterDTO
import ru.sicampus.bootcamp2025.data.userData.UserDTO
import ru.sicampus.bootcamp2025.domain.userInfo.UserRepo

class GetAllCentersUseCase(private val centerRepo: CenterRepo) {

    suspend fun invoke():List<CenterDTO>{
        return centerRepo.fetchAllCenters()
    }
}