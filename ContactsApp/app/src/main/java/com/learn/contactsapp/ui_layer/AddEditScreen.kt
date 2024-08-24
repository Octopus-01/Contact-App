package com.learn.contactsapp.ui_layer

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.io.InputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    states: ContactStates,
    navController: NavController,
    onEvent:()-> Unit
){
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null){
            val inpitStreem : InputStream? = context.contentResolver.openInputStream(uri)
                context.contentResolver.openInputStream(uri)
            val bytes = inpitStreem?.readBytes()
            if (bytes !== null)
                states.image.value = bytes
        }
    }
    Scaffold(topBar ={
        TopAppBar(title = { 
            Text(text = "String")
        })
    } ){
        Column(modifier = Modifier.padding(it)) {
            Button(onClick = { launcher.launch("image/*") }) {
                Text(text = "Pick Image")

            }
            OutlinedTextField(
                value = states.name.value, onValueChange = { states.name.value = it },
                placeholder = {
                },
                leadingIcon = {},
                shape = RoundedCornerShape(23.dp),
            )
            OutlinedTextField(
                value = states.number.value, onValueChange = { states.number.value = it },
                placeholder = {
                },
                leadingIcon = {},
                shape = RoundedCornerShape(23.dp),
            )
            OutlinedTextField(
                value = states.email.value, onValueChange = { states.email.value = it },
                placeholder = {
                },
                leadingIcon = {},
                shape = RoundedCornerShape(23.dp),
            )
            
            Button(onClick = { onEvent.invoke() }) {
                Text(text = "Save Contact")
            }
        }
    }
}