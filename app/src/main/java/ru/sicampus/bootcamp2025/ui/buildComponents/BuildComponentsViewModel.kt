package ru.sicampus.bootcamp2025.ui.buildComponents

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class BuildComponentsViewModel: ViewModel() {

    val listIconColor = mutableStateOf(Color.White)
    val mapIconColor = mutableStateOf(Color.Black)
    val profileIconColor = mutableStateOf(Color.Black)

    fun changeIconColorToWhite(nameScreen:String){
        allColorsToBlack()
        when(nameScreen){
            "listScreen" -> {listIconColor.value = Color.White}
            "mapScreen" -> { mapIconColor.value = Color.White}
            "profileScreen" -> {profileIconColor.value = Color.White}
        }
        Log.d("PPPP", profileIconColor.value.toString())

    }

    fun allColorsToBlack(){
        listIconColor.value = Color.Black
        mapIconColor.value = Color.Black
        profileIconColor.value = Color.Black
    }


}