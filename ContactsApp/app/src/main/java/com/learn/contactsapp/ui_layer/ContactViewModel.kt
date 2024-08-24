package com.learn.contactsapp.ui_layer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.contactsapp.data.database.Contact
import com.learn.contactsapp.data.database.ContactDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(var database: ContactDatabase) : ViewModel() {
    private var isSortedByName = MutableStateFlow(true)

    @OptIn(ExperimentalCoroutinesApi::class)
    private var contact = isSortedByName.flatMapLatest {
        if (it) {
            database.dao.getContectsSortByDate()
        } else {
            database.dao.getContectsSortByName()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val _state = MutableStateFlow(ContactStates())

    val state = combine(_state, contact, isSortedByName) { state, contacts, isSortedByName ->
        state.copy(
            contacts = contacts
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactStates())
    fun ChangeSorting() {
        isSortedByName.value = !isSortedByName.value
    }
    fun SaveContact(){
        val contact = Contact(
            id = state.value.id.value,
            name = state.value.name.value,
            number = state.value.number.value,
            email = state.value.email.value,
            dateofCreation = System.currentTimeMillis().toLong(),
            isactive = true,
            image = state.value.image.value

        )
        viewModelScope.launch {
            database.dao.UpsertContect(contact)
        }
        state.value.id.value = 0
        state.value.name.value = ""
        state.value.number.value = ""
        state.value.email.value = ""
        state.value.dateOfCreation.value = 0
        state.value.image.value = ByteArray(0)
    }
    fun DeleteContact(contact: Contact){
            val contact = Contact(
                id = state.value.id.value,
                name = state.value.name.value,
                number = state.value.number.value,
                email = state.value.email.value,
                dateofCreation = state.value.dateOfCreation.value,
                isactive = false
            )
        viewModelScope.launch {
            database.dao.DeleteContect(contact)
        }
        state.value.id.value = 0
        state.value.name.value = ""
        state.value.number.value = ""
        state.value.email.value = ""
        state.value.dateOfCreation.value = 0
    }

}
