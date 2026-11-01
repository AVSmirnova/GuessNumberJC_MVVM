package com.example.guessnumberjc_mvvm.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun Header(totalNumber: Int, modifier: Modifier = Modifier) {
    Text(
        text = "Введите число от 0 до $totalNumber:",
        modifier = modifier,
        fontSize = 25.sp,
        color = Color.Blue
    )
}