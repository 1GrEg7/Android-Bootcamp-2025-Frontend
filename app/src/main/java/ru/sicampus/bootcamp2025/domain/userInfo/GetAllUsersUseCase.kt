package ru.sicampus.bootcamp2025.domain.userInfo

import ru.sicampus.bootcamp2025.data.userData.UserDTO

class GetAllUsersUseCase(private val userRepo: UserRepo) {

    suspend fun invoke(): List<UserDTO> {
        return userRepo.fetchAllUsers()
    }
}