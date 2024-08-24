package com.learn.contactsapp.data.navigation

sealed class Routes( var route: String) {
    object HomeScreen : Routes("HomeScreen")
    object AddNewContact : Routes("AddNewContact")
    object Test: Routes("Test")
}