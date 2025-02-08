package ru.sicampus.bootcamp2025.ui.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.UserDTO
import ru.sicampus.bootcamp2025.data.UserRepoImpl
import ru.sicampus.bootcamp2025.domain.userInfo.GetUserUseCase
import ru.sicampus.bootcamp2025.domain.userInfo.UserRepo

class AnotherProfileViewModel:ViewModel() {

    private val _user = MutableStateFlow<UserDTO?>(null)
    val user = _user.asStateFlow()

    val userRepo: UserRepo = UserRepoImpl()

    fun getUserById(id:Int){
        val getUserUseCase = GetUserUseCase(userRepo)


        viewModelScope.launch {
            _user.update { getUserUseCase.execute(id) }
            name.value = "${_user.value!!.firstName.drop(1).dropLast(1)} ${_user.value!!.secondName.drop(1).dropLast(1) }"
            experience.value = _user.value!!.experience
            email.value = _user.value!!.email.drop(1).dropLast(1)
            age.value = _user.value!!.age
            bio.value = _user.value!!.description.drop(1).dropLast(1)
        }
    }

    val name = mutableStateOf<String>("")

    val experience = mutableStateOf<String>("")

    val email = mutableStateOf<String>("")

    val age = mutableStateOf<Int>(0)

    val bio = mutableStateOf<String>("")



}