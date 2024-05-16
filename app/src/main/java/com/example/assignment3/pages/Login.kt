package com.example.assignment3.pages


import android.graphics.Paint.Align
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.assignment3.LoggedUser
import com.example.assignment3.R
import com.example.assignment3.ui.theme.Primary
import com.example.assignment3.ui.theme.TopAppBarBackground
import com.google.firebase.database.FirebaseDatabase
import com.stevdzasan.onetap.GoogleButtonTheme
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.getUserFromTokenId
import com.stevdzasan.onetap.rememberOneTapSignInState

/*
Page to add the expenses
 */



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController) {
    var username by remember { mutableStateOf(TextFieldValue()) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var errorMessage by remember { mutableStateOf("") } // New state for error message

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Login") },
                modifier = Modifier.padding(top = 20.dp),
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = TopAppBarBackground)
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 150.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_icon),
                    contentDescription = "App Icon",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") }
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation()
                )
                Button(
                    onClick = {
                        if (username.text.isEmpty() || password.text.isEmpty()) {
                            // If username or password is empty, set error message
                            errorMessage = "Username and password cannot be empty"
                        } else {
                            // Clear error message if fields are not empty
                            errorMessage = ""
                            // Proceed with login attempt
                            val db = FirebaseDatabase.getInstance()
                            val reference = db.getReference("Users")
                            // Check if username already exists
                            reference.child(username.text).get().addOnSuccessListener { snapshot ->
                                if (!snapshot.exists()) {
                                    // Username does not exists, show error message
                                    errorMessage = "Username does not exist"
                                } else {
                                    val userData=snapshot.value as? Map<String, Any>
                                    val storedPassword = userData?.get("password") as? String
                                    if(storedPassword==password.text)
                                    {
                                        //navigate to homepage
                                        LoggedUser.username=username.text
                                        LoggedUser.isLoggedIn=true
                                        navController.navigate("expenses")
                                    }
                                    else
                                    {
                                        errorMessage = "Incorrect password"
                                    }



                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(285.dp),
                ) {
                    Text("Login")
                }
                val state = rememberOneTapSignInState()
                OneTapSignInWithGoogle(
                    state = state,
                    clientId = "948056299192-krn2batcc8bdrip8ga3ape06h3pvk2sm.apps.googleusercontent.com",
                    onTokenIdReceived = { tokenId ->
                        Log.d("LOG", tokenId)
                        Log.d("LOG", getUserFromTokenId(tokenId).toString())

                        val db = FirebaseDatabase.getInstance()
                        val reference = db.getReference("Users")
                        val usertoken = getUserFromTokenId(tokenId).toString()
                        var googleusername = extractEmailUsername(usertoken)
                        var googlefullname = extractFullName(usertoken)
                        var googleEmail = extractFullEmail(usertoken)
                        // Check if username already exists
                        reference.child(googleusername.toString()).get().addOnSuccessListener { snapshot ->
                            if (!snapshot.exists()) {
                                // add to the database
                                val googleUserData = hashMapOf<String, Any>(
                                    "fullname" to googlefullname.toString(),
                                    "username" to googleusername.toString(),
                                    "email" to googleEmail.toString(),
                                    "password" to "Qwerty@1",
                                    "totalBalance" to 0.0,
                                    "totalExpense" to 0.0,
                                    "totalIncome" to 0.0
                                )
                                reference.child(googleusername.toString()).setValue(googleUserData)
                                LoggedUser.username=googleusername.toString()
                                LoggedUser.isLoggedIn=true
                                LoggedUser.isGoogleUser=true
                                navController.navigate("expenses")

                            } else {
                                //sign in
                                LoggedUser.username=googleusername.toString()
                                LoggedUser.isLoggedIn=true
                                LoggedUser.isGoogleUser=true
                                navController.navigate("expenses")

                            }
                        }


                    },
                    onDialogDismissed = { message ->
                        Log.d("LOG", message)
                    }
                )

                Button(onClick = { state.open() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White, // Use custom color for the button background
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .width(285.dp)
                        .height(45.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.new_google),
                        contentDescription = "Sign in with Google",
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Sign in with Google")

                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = errorMessage, // Display error message if exists
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Don't have an account? Sign up",
                    color = Color(0xFF0A84FF),
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        // Handle signup option click here
                        navController.navigate("signup")
                    }
                )
            }
        }

    )
}
fun extractFullName(tokenString: String): String? {
    val fullNameRegex = "fullName=([^,]+)".toRegex()
    return fullNameRegex.find(tokenString)?.groups?.get(1)?.value
}

fun extractFullEmail(tokenString: String): String? {
    val emailRegex = "email=([^,]+)".toRegex()
    return emailRegex.find(tokenString)?.groups?.get(1)?.value
}
fun extractEmailUsername(tokenString: String): String? {
    val emailRegex = "email=([^@]+)@gmail.com".toRegex()
    return emailRegex.find(tokenString)?.groups?.get(1)?.value
}


