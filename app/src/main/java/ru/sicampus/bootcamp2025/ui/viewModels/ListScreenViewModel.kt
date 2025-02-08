package ru.sicampus.bootcamp2025.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.UserDTO
import ru.sicampus.bootcamp2025.data.UserRepoImpl
import ru.sicampus.bootcamp2025.domain.userInfo.GetAllUsersUseCase
import ru.sicampus.bootcamp2025.domain.userInfo.GetUserUseCase
import ru.sicampus.bootcamp2025.domain.userInfo.UserRepo

class ListScreenViewModel: ViewModel() {
    private val _user = MutableStateFlow<UserDTO?>(null)
    val user = _user.asStateFlow()

    private val _userList = MutableStateFlow<List<UserDTO>?>(emptyList())
    val userList = _userList.asStateFlow()

    val userRepo: UserRepo = UserRepoImpl()

    fun getUserById(id:Int){
        val getUserUseCase = GetUserUseCase(userRepo)


        viewModelScope.launch {
            _user.update { getUserUseCase.execute(id) }
        }
    }

    fun getAllUsers(){
        val getAllUserUseCase = GetAllUsersUseCase(userRepo)

        viewModelScope.launch {
            _userList.update { getAllUserUseCase.invoke() }
            Log.d("12121212", userList.value!!.joinToString() )
        }

    }
}