package ru.sicampus.bootcamp2025.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
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
import ru.sicampus.bootcamp2025.R

//@Preview(showBackground = true)
//@Composable
//fun PreviewProfileScreen(){
//    ProfileScreen()
//}

@Composable
fun ProfileScreen(
) {
    val name = "Анастасия Волочкова"
    val stazh = 5

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
                .background(color = Color(0xFFC37B5C))
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .size(45.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow),
                    contentDescription = "Назад",
                    tint = Color.Black
                )
            }
            Text(
                text = "Профиль",
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 35.dp)
            )
            IconButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(55.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.g2151),
                    contentDescription = "Редактировать",
                    tint = Color.White,
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.avatar),
                    contentDescription = "Аватар",
                    modifier = Modifier
                        .size(130.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(36.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.rectangle_4),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )

                    Text(
                        text = "Стаж $stazh лет",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black.copy(alpha = 0.9f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

            }
        }

        Column(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            InfoItem(
                title = "Электронная почта",
                value = "example123@gmail.com"
            )

            InfoItem(
                title = "Возраст",
                value = "25 лет"
            )

            InfoItem(
                title = "О себе",
                value = "Информация о себе"
            )
        }
    }
}

@Composable
private fun InfoItem(title: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

    }
}