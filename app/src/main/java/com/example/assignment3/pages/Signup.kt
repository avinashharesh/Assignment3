package com.example.assignment3.pages

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.assignment3.LoggedUser
import com.example.assignment3.ui.theme.TopAppBarBackground
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

/*
Page to add the expenses
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Signup(navController: NavController) {
    var fullName by remember { mutableStateOf(TextFieldValue()) }
    var username by remember { mutableStateOf(TextFieldValue()) }
    var email by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var confirmPassword by remember { mutableStateOf("") }
    var errorState by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var totalBalance=0.0
    var totalExpense=0.0
    var totalIncome=0.0
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Signup") },
                modifier = Modifier.padding(top = 20.dp),
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = TopAppBarBackground)
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Full Name") }
                )
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") }
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") }
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation()
                )
                TextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    //modifier = Modifier.padding(vertical = 8.dp),
                    visualTransformation = PasswordVisualTransformation(),
                )
                Button(
                    onClick = {
                        if (fullName.text.isEmpty() || username.text.isEmpty() || email.text.isEmpty() || password.text.isEmpty() || confirmPassword.isEmpty()) {
                            errorState = true
                            errorMessage = "Please fill in all fields"
                        } else if (password.text.length < 6) {
                            errorState = true
                            errorMessage = "Password must be at least 6 characters long"
                        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
                            errorState = true
                            errorMessage = "Please enter a valid email address"
                        } else if (password.text != confirmPassword) {
                            errorState = true
                            errorMessage = "Passwords do not match"
                        } else if (!password.text.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{6,}$".toRegex())) {
                            errorState = true
                            errorMessage = "Password must contain at least one uppercase letter, one digit, and one special character"
                        } else {
                            // All fields are valid, proceed with signup
                            val db = FirebaseDatabase.getInstance()
                            var reference = db.getReference("Users")

                            // Check if username already exists
                            reference.child(username.text).get().addOnSuccessListener { snapshot ->
                                if (snapshot.exists()) {
                                    // Username exists, show error message
                                    errorState = true
                                    errorMessage = "Username already exists"
                                } else {
                                    // Username doesn't exist, proceed with signup
                                    errorState = false
                                    loading = true

                                    // Perform signup process
                                    println("Sign up successful")
                                    val userData = hashMapOf<String, Any>(
                                        "fullname" to fullName.text,
                                        "username" to username.text,
                                        "email" to email.text,
                                        "password" to password.text,
                                        "totalBalance" to totalBalance.toDouble(),
                                        "totalExpense" to totalExpense.toDouble(),
                                        "totalIncome" to totalIncome.toDouble()
                                    )
                                    reference.child(username.text).setValue(userData)
                                    LoggedUser.username=username.text
                                    LoggedUser.isLoggedIn=true
                                    navController.navigate("expenses")
                                }
                            }.addOnFailureListener { exception ->
                                // Handle failure to check username existence
                                Log.e(TAG, "Error checking username existence", exception)
                                errorState = true
                                errorMessage = "Error checking username existence"
                            }
                        }
                    },
                    modifier = Modifier.padding(top = 16.dp),
                ) {
                    Text("Sign Up")
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Already have an account? Login",
                    color = Color.Gray,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        // Handle login option click here
                        navController.navigate("login")
                    }
                )

                // Display error message if there is an error
                if (errorState) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                if (loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                    )
                }
            }
        }

    )
}





