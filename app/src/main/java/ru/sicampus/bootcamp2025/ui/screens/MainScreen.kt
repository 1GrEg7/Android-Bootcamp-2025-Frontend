package ru.sicampus.bootcamp2025.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.ui.viewModels.MainScreenViewModel
import java.io.IOException
import kotlin.concurrent.thread

//@Preview(showBackground = true)
//@Composable
//fun PreviewMainScreenScreen() {
//    MainScreen()
//}


@Composable
fun MainScreen(
    toAuthorizationScreen:  () -> Unit,
    toProfileScreen:() -> Unit,
    vm: MainScreenViewModel
){
    Column() {
        Row(
            modifier = Modifier.fillMaxSize().weight(2f),
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

        Column(modifier = Modifier.fillMaxSize().weight(7f)) {
            val list =  vm.userList.collectAsState()
            //Log.d("1212121PPPP", list.value.orEmpty().toString())
            LazyColumn {
                items(list.value.orEmpty()){ item ->

                    listElement(_name = item.username, _date = item.email, _address = item.phoneNumber)
//                    Text(text = item.username)
//                    Text(text = item.phoneNumber)
//                    Text(text = item.email)
//                    Spacer(modifier = Modifier.size(10.dp))
                }
            }



        }



        Row(
            modifier = Modifier.fillMaxSize().weight(0.7f).background(Color(0xFFD0634B)),
            horizontalArrangement =Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.clickable { toAuthorizationScreen() },
                painter = painterResource(id = R.drawable.exit),
                contentDescription = "Описание изображения")

            Box(contentAlignment = Alignment.Center){
                Icon(painter = painterResource(id = R.drawable.map),
                    contentDescription = "Описание изображения")
                Icon(modifier = Modifier.padding(bottom = 5.dp),painter = painterResource(id = R.drawable.circleinmap),
                    contentDescription = "Описание изображения")
            }


            Icon(
                modifier = Modifier.clickable { toProfileScreen(); },
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Описание изображения")

        }

    }
}

@Composable
fun listElement(_name:String, _date:String, _address:String){
    val name = _name.drop(1).dropLast(1)
    val date = _date.drop(1).dropLast(1)
    val address = _address.drop(1).dropLast(1)
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(20.dp)
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Row(modifier = Modifier.padding(start = 25.dp, top = 30.dp, bottom = 15.dp)) {
            Icon(
                modifier = Modifier.size(30.dp).padding(end = 5.dp),
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Описание изображения"
            )
            Icon(
                modifier = Modifier.size(30.dp).padding(end = 5.dp),
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Описание изображения"
            )
            Icon(
                modifier = Modifier.size(30.dp).padding(end = 5.dp),
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Описание изображения"
            )
            Icon(
                modifier = Modifier.size(30.dp).padding(end = 5.dp),
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Описание изображения"
            )
            Icon(
                modifier = Modifier.size(30.dp).padding(end = 5.dp),
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Описание изображения"
            )
        }

        Text(
            text = "Волонтер",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 25.dp)
        )
        Text(
            text = address,
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 25.dp, top = 8.dp)
        )



        Row(modifier = Modifier.padding(start = 25.dp, top = 25.dp).fillMaxWidth()) {
            Box(modifier = Modifier.size(50.dp).clip(CircleShape),) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "Описание изображения",
                )
            }
            Column {
                Text(
                    text = name, Modifier
                        .padding(start = 20.dp, top = 5.dp),
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = date,
                    modifier = Modifier.padding(start = 19.dp, top = 5.dp),
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))
    }

}
