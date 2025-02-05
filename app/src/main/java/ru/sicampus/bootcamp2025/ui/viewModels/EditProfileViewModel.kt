package ru.sicampus.bootcamp2025.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.ui.viewModels.AuthorizationViewModel.State


class EditProfileViewModel : ViewModel(){
    private val _state = MutableStateFlow<UState>(UState.Input)
    val state = _state.asStateFlow()

    sealed interface UState{
        object Loading: UState
        object Success: UState
        object Input: UState
        data class Error (val text: String): UState
    }

    fun Edit(name: String, lastname: String, age: String,experience:String,bio:String) {
        if (name.isBlank() || lastname.isBlank() || age.isBlank() || experience.isBlank() || bio.isBlank()) {
            _state.update { UState.Error("Поля не должны быть пустыми") }
            return
        }
        val ageInt = age.toIntOrNull()
        if (ageInt == null) {
            _state.update { UState.Error("Возраст должен быть числом") }
            return
        }

        // Проверка стажа (дополнительно)
        val experienceInt = experience.toIntOrNull()
        if (experience.isEmpty()) {
            _state.update { UState.Error("Укажите стаж") }
            return
        }

        _state.update { UState.Loading }
        viewModelScope.launch {
            kotlinx.coroutines.delay(2000)
            if (name.isNotBlank() || lastname.isNotBlank() || age.isNotBlank() || experience.isNotBlank() || bio.isNotBlank()) {
                _state.update { UState.Success }
            }
            else {
                _state.update { UState.Error("Неверные данные") }
            }
        }
    }

    fun resetState() {
        _state.update { UState.Input }
    }
}