package com.example.guessnumberjc_mvvm.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppButton(btnText:String,
              onPress:()->Unit,
              isWin: Boolean=false){
    Button (onClick={onPress()},
        enabled = isWin){
        Text(text = btnText, modifier = Modifier)
    }
}