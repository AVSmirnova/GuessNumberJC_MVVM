package com.example.guessnumberjc_mvvm.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppTextField(
    message: String,
    placeholderText:String = "Только цифры!!",
    onBtnClick: (String) -> Unit,
    onClearClick:() -> Unit

)
{
    TextField(
        value = message,
        textStyle = TextStyle(fontSize = 25.sp),
        onValueChange = onBtnClick,
        placeholder = { Text(placeholderText) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.White
        ),
        modifier = Modifier.padding(12.dp).fillMaxWidth(),
        trailingIcon = {
            if (message.isNotEmpty()) {
                IconButton(
                    onClick = { onClearClick() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Очистить"
                    )
                }
            }
        }

    )
}