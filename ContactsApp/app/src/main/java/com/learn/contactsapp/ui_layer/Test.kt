package com.learn.contactsapp.ui_layer

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Test(modifier: Modifier) {
    Box(modifier = Modifier){
        Text(text = "Hello")
    }
}