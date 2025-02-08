package ru.sicampus.bootcamp2025.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.ui.buildComponents.BottomMenu
import ru.sicampus.bootcamp2025.ui.buildComponents.BuildComponentsViewModel
import ru.sicampus.bootcamp2025.ui.buildComponents.listElement
import ru.sicampus.bootcamp2025.ui.viewModels.AnotherProfileViewModel
import ru.sicampus.bootcamp2025.ui.viewModels.ListScreenViewModel

//@Preview(showBackground = true)
//@Composable
//fun PreviewListScreenScreen() {
//    ListScreen()
//}


@Composable
fun ListScreen(
    toAuthorizationScreen: () -> Unit ={},
    toProfileScreen: () -> Unit = {},
    //toMapScreen: () -> Unit,
    toListScreen: () -> Unit ={},
    vm: ListScreenViewModel = viewModel(),
    bottomMenuViewModel: BuildComponentsViewModel = viewModel(),
    anotherProfileViewModel: AnotherProfileViewModel,
    toAnotherProfileScreen: () -> Unit = {},
){
    Column() {

        Row(
            modifier = Modifier.fillMaxSize().weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(modifier = Modifier,text ="Volunteer",fontSize = 40.sp, color = Color.Red)
        }
        Row(
            modifier = Modifier.fillMaxSize().weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Активные волонтеры",fontSize = 30.sp)
        }

        Row(
            modifier = Modifier.fillMaxSize().weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                modifier = Modifier.padding(top = 10.dp),
                value = vm.editTextFieldSearch.value,
                onValueChange = { vm.editTextFieldSearch.value = it },
                shape = RoundedCornerShape(8.dp),
                placeholder = {
                    Text(
                        text = "Введите центр волонтеров",
                        color = Color.Gray
                    )
                }
            )
            Icon(
                modifier = Modifier.padding(start = 10.dp,top = 5.dp).clickable {
                    if (vm.editTextFieldSearch.value ==""){
                        vm.returnFullUserList()
                    }else{
                        vm.returnFullUserList()
                        vm.getUsersByCenterName(vm.editTextFieldSearch.value)
                    }

                },
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "Описание изображения",
                tint = Color.Gray
            )
        }
        Spacer(Modifier.height(20.dp))

        Column(modifier = Modifier.fillMaxSize().weight(7f)) {
            val list =  vm.userList.collectAsState()
            //Log.d("1212121PPPP", list.value.orEmpty().toString())
            LazyColumn {
                items(list.value.orEmpty()){ item ->
                    listElement(_name = item.firstName, _date = item.email, _address = item.volunteerCenter, toAnotherProfileScreen = { toAnotherProfileScreen()}, anotherProfileViewModel = anotherProfileViewModel, userId = item.id )
                }
            }



        }



       BottomMenu(
           //toMapScreen =  toMapScreen,
           toProfileScreen = { toProfileScreen() },
           toAuthorizationScreen ={toAuthorizationScreen()} ,
           toListScreen = {toListScreen()},
           vm = bottomMenuViewModel
       )

    }
}

