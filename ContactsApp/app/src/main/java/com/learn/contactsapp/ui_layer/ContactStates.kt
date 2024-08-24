package com.learn.contactsapp.ui_layer

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.learn.contactsapp.data.database.Contact

data class ContactStates(
    val contacts : List<Contact> = emptyList(),
    val id : MutableState<Int> = mutableIntStateOf(0),
    val name : MutableState<String> = mutableStateOf(""),
    val number : MutableState<String> = mutableStateOf(""),
    val email : MutableState<String> = mutableStateOf(""),
    val dateOfCreation : MutableState<Long> = mutableStateOf(0),
    val image: MutableState<ByteArray> = mutableStateOf(ByteArray(0))

    )