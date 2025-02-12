package ru.sicampus.bootcamp2025.ui.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.annotations.Until
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.ui.viewModels.CentersViewModel


//@Preview(showBackground = true)
//@Composable
//fun CentersScreenPreview(){
//    CentersScreen()
//}

@Composable
fun CentersScreen(
    toListScreen: ()-> Unit,
    centersViewModel: CentersViewModel
){
    // Данные для таблицы, каждый элемент – пара значений для двух столбцов
    Column(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.fillMaxSize().weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(modifier = Modifier,text ="Volunteer Centers",fontSize = 40.sp, color = Color.Red)
        }
        Column(
            modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .padding(start = 20.dp)

        ) {
            Icon(
                modifier = Modifier.clickable {
                    toListScreen()
                },
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "Описание изображения",
                tint = Color.Black
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(10f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Обходим список данных и создаём карточки со строками
            centersViewModel.centerList.collectAsState().value!!.forEach { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.TopStart
                        ) {
                            Text(
                                text = "${item.name.drop(1).dropLast(1)}:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = item.location.drop(1).dropLast(1),
                            )
                        }

                    }
                }
            }
        }

    }


}