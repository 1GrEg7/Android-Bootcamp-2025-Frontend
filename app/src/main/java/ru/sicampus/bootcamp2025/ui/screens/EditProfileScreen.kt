package ru.sicampus.bootcamp2025.ui.screens
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import ru.sicampus.bootcamp2025.ui.viewModels.EditProfileViewModel
import android.content.SharedPreferences
import androidx.compose.ui.platform.LocalContext

//@Preview(showBackground = true)
//@Composable
//fun PreviewEditScreen(){
//    EditProfileScreen()
//}

@Composable
fun EditProfileScreen(
    onSave: () -> Unit,
    toProfileScreen: () -> Unit,
    viewModel: EditProfileViewModel
) {

    Column(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize().weight(2f).background(Color(0xFFC37B5C))) {

            Row(
                modifier = Modifier.fillMaxSize().weight(2f).padding(top = 30.dp),

            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow),
                    contentDescription = "",
                    modifier = Modifier.weight(1f).wrapContentSize().clickable {
                        toProfileScreen()
                    }
                )
                Box(
                    modifier = Modifier.weight(3f).fillMaxSize().padding(end = 5.dp),
                    contentAlignment = Alignment.TopCenter
                    ){
                    Text(
                        text = "Профиль",
                        fontSize = 30.sp
                    )
                }

                Text("", modifier = Modifier.weight(0.9f).fillMaxSize())
            }


            Box(modifier = Modifier.fillMaxSize().weight(3f)){
                Image(
                    modifier = Modifier.fillMaxSize().zIndex(0f),
                    painter = painterResource(R.drawable.avatar),
                    contentDescription = ""
                )
                Box(modifier = Modifier.fillMaxSize().zIndex(1f), contentAlignment = Alignment.BottomCenter){
                    Icon(
                        modifier = Modifier.align(Alignment.BottomCenter).size(30.dp),
                        painter = painterResource(R.drawable.add_plus),
                        contentDescription = ""
                    )
                }
            }


        }

        Column(
            modifier = Modifier.fillMaxSize().weight(5f).background(Color.White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextAndField(
                modifier = Modifier.padding(top = 30.dp),
                label = "Имя",
                value = viewModel.firstName.value,
                onValueChange = { viewModel.firstName.value = it }
            )

            TextAndField(
                modifier = Modifier.padding(top = 30.dp),
                label = "Фамилия",
                value = viewModel.lastName.value,
                onValueChange = { viewModel.lastName.value = it }
            )

            // Возраст
            TextAndField(
                modifier = Modifier.padding(top = 30.dp),
                label = "Возраст",
                value = viewModel.age.value,
                onValueChange = { viewModel.age.value = it }
            )

            // Стаж
            TextAndField(
                modifier = Modifier.padding(top = 30.dp),
                label = "Стаж",
                value = viewModel.experience.value,
                onValueChange = { viewModel.experience.value = it }
            )

            // О себе
            TextAndField(
                modifier = Modifier.padding(top = 30.dp),
                label = "О себе",
                value = viewModel.aboutMe.value,
                onValueChange = { viewModel.aboutMe.value = it }
            )


            Spacer(Modifier.size(40.dp))
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceAround) {
                Button(
                    onClick = {
                        viewModel.saveProfile()
                        onSave()
                        },
                    modifier = Modifier.width(140.dp)
                        .height(40.dp)
                        .clickable {
                        },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xFF47A76A),
                        contentColor = Color.White,
                        disabledContainerColor = Color.LightGray,
                        disabledContentColor = Color.Gray
                    ),
                    shape = RoundedCornerShape(8.dp))
                {
                    Text("Сохранить")
                }
            }

            Spacer(Modifier.size(40.dp))
        }
    }
}

@Composable
fun TextAndField(modifier: Modifier = Modifier, label: String, value: String, onValueChange: (String) -> Unit) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 18.sp
        )
        OutlinedTextField(
            modifier = Modifier.padding(top = 10.dp),
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(8.dp),
        )
    }
}




//OutlinedTextField(
//value = bio.value,
//onValueChange = { bio.value = it },
//modifier = Modifier.width(280.dp)
//.height(90.dp)
//.padding(start= 60.dp) ,
//shape = RoundedCornerShape(8.dp),
//label = {
//    Text(
//        text = "Напишите о себе",
//        style = MaterialTheme.typography.bodyMedium
//
//    )
//}
//)


