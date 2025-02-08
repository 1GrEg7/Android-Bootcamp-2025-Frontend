package ru.sicampus.bootcamp2025.ui.viewModels


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class UserProfile(
    val firstName: String,
    val lastName: String,
    val age: String,
    val experience: String,
    val aboutMe: String
)
class EditProfileViewModel : ViewModel() {

    var firstName = mutableStateOf("")
    var lastName = mutableStateOf("")
    var age = mutableStateOf("")
    var experience = mutableStateOf("")
    var aboutMe = mutableStateOf("")


    var isSavingError = mutableStateOf(false)


    fun saveProfile() {
        try {

            val profile = UserProfile(
                firstName = firstName.value,
                lastName = lastName.value,
                age = age.value,
                experience = experience.value,
                aboutMe = aboutMe.value
            )


        } catch (e: Exception) {
            isSavingError.value = true
            e.printStackTrace()
        }
    }


    fun resetProfile() {
        firstName.value = ""
        lastName.value = ""
        age.value = ""
        experience.value = ""
        aboutMe.value = ""
    }
}
