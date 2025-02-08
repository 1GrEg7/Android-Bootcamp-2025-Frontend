package ru.sicampus.bootcamp2025.ui.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.toUpperCase
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


    private val _userList = MutableStateFlow<List<UserDTO>?>(emptyList())
    val userList = _userList.asStateFlow()

    val editTextFieldSearch = mutableStateOf("")
    val allUsersPermanent = MutableStateFlow<List<UserDTO>?>(emptyList())

    val userRepo: UserRepo = UserRepoImpl()



    fun getAllUsers(){
        val getAllUserUseCase = GetAllUsersUseCase(userRepo)

        viewModelScope.launch {
            val list = getAllUserUseCase.invoke()
            _userList.update { list }
            allUsersPermanent.update { list }
            Log.d("12121212", userList.value!!.joinToString() )
        }

    }

    fun returnFullUserList(){
        _userList.update { allUsersPermanent.value }
    }


    fun getUsersByCenterName(centerName:String){

        val _centerName = centerName.trim().uppercase()
        _userList.update { userList.value!!.filter {
            Log.d("12121212QQQQ", it.volunteerCenter )
            Log.d("12121212QQQQ", _centerName  )
            it.volunteerCenter.drop(1).dropLast(1).uppercase() == _centerName
            }
        }
        Log.d("12121212QQQQ", _userList.value!!.joinToString() )

    }
}