package com.example.foodorderingapp

import android.R.id.message
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun EditProfileScreen(navController: NavHostController) {
    val user = UserRepository.currentUser ?: return

    var email by remember { mutableStateOf(user.email) }
    var password by remember { mutableStateOf(user.password) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Edit Profile", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                val it = null
                email = it
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                UserRepository.updateUser(email, password)
                message = "Profile updated"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Changes")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                UserRepository.logout()
                navController.navigate("login") {
                    popUpTo("edit_profile") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }

        if (message.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(message)
        }
    }
}


