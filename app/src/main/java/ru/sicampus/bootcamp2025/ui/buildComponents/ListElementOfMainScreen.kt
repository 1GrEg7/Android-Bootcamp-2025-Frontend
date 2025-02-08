package ru.sicampus.bootcamp2025.ui.buildComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sicampus.bootcamp2025.R

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
