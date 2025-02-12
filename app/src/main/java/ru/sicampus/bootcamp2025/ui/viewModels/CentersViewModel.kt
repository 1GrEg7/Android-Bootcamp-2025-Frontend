package ru.sicampus.bootcamp2025.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.centersData.CenterDTO
import ru.sicampus.bootcamp2025.data.centersData.CenterRepoImpl
import ru.sicampus.bootcamp2025.data.userData.UserDTO
import ru.sicampus.bootcamp2025.data.userData.UserRepoImpl
import ru.sicampus.bootcamp2025.domain.centersInfo.CenterRepo
import ru.sicampus.bootcamp2025.domain.centersInfo.GetAllCentersUseCase
import ru.sicampus.bootcamp2025.domain.userInfo.GetAllUsersUseCase
import ru.sicampus.bootcamp2025.domain.userInfo.UserRepo

class CentersViewModel: ViewModel() {

    private val _centerList = MutableStateFlow<List<CenterDTO>?>(emptyList())
    val centerList = _centerList.asStateFlow()

    val centerRepo: CenterRepo = CenterRepoImpl()


    fun getAllCenters(){
        val getAllCenterUseCase = GetAllCentersUseCase(centerRepo)

        viewModelScope.launch {
            val list = getAllCenterUseCase.invoke()
            _centerList.update { list }
           // allUsersPermanent.update { list }
            //Log.d("12121212", userList.value!!.joinToString() )
        }

    }

}