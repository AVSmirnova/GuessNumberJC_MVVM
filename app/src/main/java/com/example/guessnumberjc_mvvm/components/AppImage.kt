package com.example.guessnumberjc_mvvm.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun AppImage (imageResource:Int,
              description:String,
){
    Image(painter = painterResource(id=imageResource),
        contentDescription = description,
        contentScale = ContentScale.Crop)
}