package ru.sicampus.bootcamp2025.domain.userInfo

import ru.sicampus.bootcamp2025.data.userData.UserDTO

class GetUserUseCase(private val userRepo: UserRepo) {

    suspend fun execute(id:Int): UserDTO {
        return userRepo.fetchUser(id)
    }

}