package com.example.foodorderingapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ForgotPasswordScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Forgot Password", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                message = if (UserRepository.userExists(email)) {
                    "Password reset link sent to $email"
                } else {
                    "User not found"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send Reset Link")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { navController.popBackStack() }) {
            Text("Back to Login")
        }

        if (message.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = message)
        }
    }
}
