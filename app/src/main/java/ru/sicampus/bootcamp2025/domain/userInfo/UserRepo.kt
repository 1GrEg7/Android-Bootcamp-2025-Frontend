package ru.sicampus.bootcamp2025.domain.userInfo

import ru.sicampus.bootcamp2025.data.userData.UserDTO

interface UserRepo {

    suspend fun fetchUser(id:Int): UserDTO

    suspend fun fetchAllUsers(): List<UserDTO>
}