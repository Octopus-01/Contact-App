package com.learn.contactsapp.data.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.learn.contactsapp.ui_layer.AddEditScreen
import com.learn.contactsapp.ui_layer.AllContactScreen
import com.learn.contactsapp.ui_layer.ContactViewModel
import com.learn.contactsapp.ui_layer.Test

@Composable
fun NavHostGraph(modifier: Modifier = Modifier,
                 viewModels: ContactViewModel,
                 navController: NavHostController)
{
    val state by viewModels.state.collectAsState()
    NavHost(navController = navController,
        startDestination = Routes.HomeScreen.route) {
        composable(Routes.HomeScreen.route) {
            AllContactScreen(viewModels = viewModels, states = state, navController = navController)
        }
        composable(Routes.AddNewContact.route) {
            AddEditScreen(states = viewModels.state.collectAsState().value, navController = navController){
                viewModels.SaveContact()
            }
        }
        composable(Routes.Test.route){
            Test(modifier = Modifier)
        }
    }
}
