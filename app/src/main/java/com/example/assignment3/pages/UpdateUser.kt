package com.example.assignment3.pages


import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.assignment3.LoggedUser
import com.example.assignment3.ui.theme.TopAppBarBackground
import com.google.firebase.database.FirebaseDatabase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateUser(
    navController: NavController,
    FullName: String,
    TotalBalance:Double,
    Email:String,
    Password:String
) {
    var fullName by remember { mutableStateOf(TextFieldValue(FullName)) }
    var totalBalance by remember { mutableStateOf(TextFieldValue(TotalBalance.toString())) }
    var email by remember { mutableStateOf(TextFieldValue(Email)) }
    var password by remember { mutableStateOf(TextFieldValue(Password)) }
    var confirmPassword by remember { mutableStateOf(Password) }
    var errorState by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { androidx.compose.material3.Text(text = "Update User Information") },
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
                    label = { androidx.compose.material3.Text("Full Name") }
                )
                TextField(
                    value = totalBalance,
                    onValueChange = { totalBalance = it },
                    label = { androidx.compose.material3.Text("Total Balance") }
                )

                // Display email, password, and confirm password fields only if not a Google user
                if (!LoggedUser.isGoogleUser) {
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { androidx.compose.material3.Text("Email") }
                    )
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { androidx.compose.material3.Text("Password") },
                        visualTransformation = PasswordVisualTransformation()
                    )
                    TextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { androidx.compose.material3.Text("Confirm Password") },
                        modifier = Modifier.padding(vertical = 8.dp),
                        visualTransformation = PasswordVisualTransformation(),
                    )
                }

                Button(
                    onClick = {
                        if (fullName.text.isEmpty() || totalBalance.text.isEmpty() || email.text.isEmpty() || password.text.isEmpty() || confirmPassword.isEmpty()) {
                            errorState = true
                            errorMessage = "Please fill in all fields"
                        } else if (password.text.length < 6) {
                            errorState = true
                            errorMessage = "Password must be at least 6 characters long"
                        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
                            errorState = true
                            errorMessage = "Please enter a valid email address"
                        } else if (password.text != confirmPassword) {
                            errorState = true
                            errorMessage = "Passwords do not match"
                        } else {
                            // All fields are valid, proceed with signup
                            val db = FirebaseDatabase.getInstance()
                            var reference = db.getReference("Users")
                            reference.child(LoggedUser.username).get().addOnSuccessListener { snapshot ->
                                if (!snapshot.exists()) {
                                    // Username does not exists, show error message
                                    println("User does not exist")
                                } else {
                                    val doubleValue: Double? = totalBalance.text.toDoubleOrNull()
                                    if(doubleValue==null)
                                    {
                                        errorState=true
                                        errorMessage="Invalid Total Balance"
                                    }
                                    else
                                    {
                                        errorState=false
                                        errorMessage=""
                                    }
                                    val updatedUserData = hashMapOf<String, Any>(
                                        "fullname" to fullName.text,
                                        "totalBalance" to (doubleValue?.toDouble() ?: 0.0),
                                        "email" to email.text,
                                        "password" to password.text
                                    )
                                    reference.child(LoggedUser.username).updateChildren(updatedUserData)
                                    navController.navigate("account")



                                }
                            }

                        }
                    },
                    modifier = Modifier.padding(top = 16.dp),
                ) {
                    androidx.compose.material3.Text("Update")
                }

                // Display error message if there is an error
                if (errorState) {
                    androidx.compose.material3.Text(
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
