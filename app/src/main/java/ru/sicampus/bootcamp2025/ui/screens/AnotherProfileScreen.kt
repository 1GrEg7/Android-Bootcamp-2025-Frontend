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
import androidx.compose.runtime.mutableStateOf
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
import ru.sicampus.bootcamp2025.ui.viewModels.AnotherProfileViewModel

//@Preview(showBackground = true)
//@Composable
//fun PreviewProfileScreen(){
//    ProfileScreen()
//}

@Composable
fun AnotherProfileScreen(
    toListScreen: () -> Unit,
    anotherProfileViewModel: AnotherProfileViewModel

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

                Box(modifier = Modifier.fillMaxSize().weight(1f))

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
                    text = anotherProfileViewModel.name.value,
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
                        text = "Cтаж: ${anotherProfileViewModel.experience.value}г",
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
                InfoItem("Электронная почта", anotherProfileViewModel.email.value)
                InfoItem("Возраст", anotherProfileViewModel.age.value.toString())
                InfoItem("О себе", anotherProfileViewModel.bio.value)

            }
        }

    }



}




