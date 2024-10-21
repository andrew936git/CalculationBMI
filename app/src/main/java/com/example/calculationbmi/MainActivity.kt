package com.example.calculationbmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Table()

        }
    }
}

@Composable
private fun Table() {
    val height = remember{
        mutableDoubleStateOf(0.0)
    }

    val massa = remember{
        mutableDoubleStateOf(0.0)
    }
    val imt = remember {
        derivedStateOf {
            if(height.doubleValue == 0.0) 0.0
                else{
                massa.doubleValue / ((height.doubleValue / 100) * (height.doubleValue / 100)) }}
    }

    val interpretation = remember {
        derivedStateOf{
            when (imt.value){
                in 0.0.. 0.1 -> "Недостаточно данных"
                in 0.2..< 16.0 -> "Выраженный дефицит массы тела"
                in 16.0 ..18.5 -> "Недостаточная масса тела"
                in 18.6 ..25.0 -> "Нормальная масса тела"
                in 25.1..30.0 -> "Избыточная масса тела (предожирение)"
                in 30.1..35.0 -> "Ожирение 1-ой степени"
                in 35.1..40.0 -> "Ожирение 2-ой степени"
                else -> "Ожирение 3-ей степени"
            }

        }
    }
    Surface(
        modifier = Modifier
            .padding(top = 40.dp, start = 8.dp, end = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
        ) {
            Text(
                "Калькулятор ИМТ",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 35.dp)
                .background(Color.Cyan)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Рост:",
                    fontSize = 24.sp
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${height.doubleValue}",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .clickable { height.doubleValue += 5 }
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Вес:",
                    fontSize = 24.sp
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${massa.doubleValue}",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .clickable { massa.doubleValue += 5 }
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Коэффициент ИМТ:",
                    fontSize = 24.sp
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${imt.value}",
                    fontSize = 24.sp
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(12.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .fillMaxWidth()
            ) {
                Text(
                    text = interpretation.value,
                    fontSize = 24.sp
                )
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(12.dp)

                    .fillMaxWidth()
            ) {
                Text(
                    text = "Сбросить",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .clickable {
                            massa.doubleValue = 0.0
                            height.doubleValue = 0.0
                        }
                )
            }

        }
    }

}

