package com.learn.contactsapp.ui_layer

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.learn.contactsapp.data.navigation.Routes

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllContactScreen(viewModels: ContactViewModel,states: ContactStates,navController:NavController)  {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier.clickable {
                viewModels.ChangeSorting()

            },
                title = { Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)})
        }, floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigate(Routes.AddNewContact .route)}) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            items(states.contacts){

                var bitmap : Bitmap? = null
                if (it.image != null)
                bitmap = BitmapFactory.decodeByteArray(it.image, 0 , it.image!!.size)
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)) {
                    Row {
                        if (bitmap != null){
                            Image(bitmap = bitmap.asImageBitmap(), contentDescription = null, modifier = Modifier.size(50.dp))
                        }
                        Column(modifier =Modifier.clickable {
                            states.id.value = it.id
                            states.number.value = it.number
                            states.name.value = it.name
                            states.email.value = it.email
                            states.dateOfCreation.value = it.dateofCreation
                            navController.navigate(Routes.AddNewContact.route)
                        } ) {
                            Text(text = it.name)
                            Text(text = it.number)
                            Text(text = it.email)
                            Button(onClick = {
                                val intent = Intent(Intent.ACTION_CALL)
                                intent.data = Uri.parse("tel:${it.number}")
                                context.startActivity(intent)

                            }) {
                                Text(text = "Call")
                            }
                        }
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = null
                        , modifier = Modifier.clickable {
                                states.id.value = it.id
                                states.number.value = it.number
                                states.name.value = it.name
                                states.email.value = it.email
                                states.dateOfCreation.value = it.dateofCreation
                                viewModels.DeleteContact(it)

                            }
                        )

                    }
                }
            }
        }
    }
}