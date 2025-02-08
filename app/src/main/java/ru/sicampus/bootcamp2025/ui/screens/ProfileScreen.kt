package ru.sicampus.bootcamp2025.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.ui.buildComponents.BottomMenu
import ru.sicampus.bootcamp2025.ui.buildComponents.BuildComponentsViewModel

//@Preview(showBackground = true)
//@Composable
//fun PreviewProfileScreen(){
//    ProfileScreen()
//}

@Composable
fun ProfileScreen(
    onEditClick: () -> Unit,
    toAuthorizationScreen: () -> Unit,
    toProfileScreen: () -> Unit,
   // toMapScreen: () -> Unit,
    toListScreen: () -> Unit,
    bottomMenuViewModel: BuildComponentsViewModel
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize().weight(3f).background(Color(0xFFC37B5C))) {

            Row(modifier = Modifier.fillMaxSize().weight(0.8f)){
                Box(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        painter = painterResource(R.drawable.arrow),
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            toListScreen()
                        }
                        )
                }

                Box(
                    modifier = Modifier.fillMaxSize().weight(3f),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Профиль",
                        fontSize = 30.sp)
                }

                Box(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        painter = painterResource(R.drawable.g2151),
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            onEditClick()
                        }

                    )
                }

            }

            Box(modifier = Modifier.fillMaxSize().weight(1.2f)){
                Image(
                    modifier = Modifier.fillMaxSize().zIndex(0f),
                    painter = painterResource(R.drawable.avatar),
                    contentDescription = ""
                )
            }

            Box(
                modifier = Modifier.fillMaxSize().weight(0.5f),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Анастасия Волочкова",
                    fontSize = 18.sp
                    )

            }
            Row(
                modifier = Modifier.fillMaxSize().weight(0.5f),
            ){
                Box(
                    modifier = Modifier.fillMaxSize().weight(1f)
                )
                Box(
                    modifier = Modifier.fillMaxSize().weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(bottom = 5.dp)
                    ,
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Стаж 5 лет",
                        fontSize = 18.sp)
                }
                Box(
                    modifier = Modifier.fillMaxSize().weight(1f)
                )
            }
            Box(
                modifier = Modifier.fillMaxSize().weight(0.2f),

            )

        }

        Column(modifier = Modifier.fillMaxSize().weight(4f).background(Color.White)) {
            Column(modifier = Modifier.padding(40.dp)) {
                InfoItem("Электронная почта", "example@mail.ru")
                InfoItem("Возраст", "22")
                InfoItem("О себе", "eавдлыджлыждплыдапыл\ndslkfdkjflskdjdflkjlksjfkljfaldskj\nsdkjfkajdlkajfkjfkads\nadjshfsdfjsnfjaskdhfkjafhakjfhkjadfh ")

            }
        }

        BottomMenu(
           // toMapScreen =  toMapScreen,
            toProfileScreen = { toProfileScreen() } ,
            toAuthorizationScreen = { toAuthorizationScreen() },
            toListScreen = { toListScreen() },
            vm = bottomMenuViewModel
        )

    }



}



@Composable
fun InfoItem(title: String, text: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        color = Color.Gray.copy(0.7f)
        )
    Spacer(modifier = Modifier.size(8.dp))
    Text(
        text = text,
        fontSize = 18.sp,
    )
    Spacer(modifier = Modifier.size(15.dp))
}

