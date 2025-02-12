package ru.sicampus.bootcamp2025.ui.buildComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.sicampus.bootcamp2025.R



@Composable
fun BottomMenu(
    modifier: Modifier = Modifier,
    toAuthorizationScreen: () -> Unit,
    toProfileScreen: () -> Unit,
    toMapScreen: () -> Unit,
    toListScreen: () -> Unit,
    vm: BuildComponentsViewModel
){
    Row(
        modifier = modifier.fillMaxWidth().height(60.dp).background(Color(0xFFD0634B)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier.clickable { toAuthorizationScreen() }
        ){
            Icon(
                painter = painterResource(id = R.drawable.exit),
                contentDescription = "Описание изображения"
            )


        }


        Box(
            modifier = Modifier.clickable { toListScreen() }
        ){
            Icon(
                modifier = Modifier.size(55.dp),
                painter = painterResource(id = R.drawable.people_icon),
                contentDescription = "Описание изображения",
                tint = vm.listIconColor.value
            )
        }


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.clickable { toMapScreen() }
        ){
            Icon(
                painter = painterResource(id = R.drawable.map),
                contentDescription = "Описание изображения",
                tint = vm.mapIconColor.value
                )
            Icon(
                modifier = Modifier.padding(bottom = 5.dp),
                painter = painterResource(id = R.drawable.circleinmap),
                contentDescription = "Описание изображения",
                tint = vm.mapIconColor.value
            )

        }



        Box(
            modifier = Modifier.clickable { toProfileScreen() }
        ){
            Icon(
                modifier = Modifier.clickable { toProfileScreen(); },
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Описание изображения",
                tint = vm.profileIconColor.value
            )
        }


    }
}