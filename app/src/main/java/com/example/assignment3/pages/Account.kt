package com.example.assignment3.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.assignment3.LoggedUser
import com.example.assignment3.R
import com.example.assignment3.ui.theme.TopAppBarBackground
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


/*
Page initially set to have the feature to log-out of the account.
*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Account(navController: NavHostController) {
    var fullName by remember { mutableStateOf("") }
    var totalBalance by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var passsword by remember { mutableStateOf("") }
    val db = FirebaseDatabase.getInstance()
    val reference = db.getReference("Users")
    // Check if username already exists
    reference.child(LoggedUser.username).get().addOnSuccessListener { snapshot ->
        if (!snapshot.exists()) {
            // Username does not exists, show error message
            println("User does not exist")
        } else {
            val userData=snapshot.value as? Map<String, Any>
            val retrieved_name = userData?.get("fullname") as? String
            val retrieved_balance=userData?.get("totalBalance")?.toString()?.toDoubleOrNull() ?: 0.0
            val retrieved_email = userData?.get("email") as? String
            val retrieved_password = userData?.get("password") as? String
            fullName=retrieved_name.toString()
            totalBalance=retrieved_balance.toString()
            email=retrieved_email.toString()
            passsword=retrieved_password.toString()

        }
    }
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Account") },
                modifier = Modifier.padding(top = 20.dp),
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = TopAppBarBackground)
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding) // Apply innerPadding here
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ProfileImage(
                    imageResId = R.drawable.account_circle,
                    userName = fullName,
                    totalBalance = totalBalance,
                    onUpdateClick = {
                        // Handle update user click here
                        navController.navigate("updateuser/${fullName}/${totalBalance}/${email}/${passsword}")
                    },
                    onDeleteClick = {
                        // Handle delete account click here
                        // For example, you can call a function to delete the account
                        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
                        var reference: DatabaseReference = database.getReference(LoggedUser.username)
                        reference.removeValue()
                            .addOnSuccessListener {
                                // Data successfully deleted
                                println("Data deleted successfully.")
                            }
                            .addOnFailureListener { exception ->
                                // Failed to delete the data
                                println("Failed to delete data: $exception")
                            }
                        reference=db.getReference("Users").child(LoggedUser.username)
                        val delete_task=reference.removeValue()
                        delete_task.addOnSuccessListener {
                            println("Successfully deleted")
                        }.addOnFailureListener{
                            println("Deletion Failed")
                        }
                        LoggedUser.username=""
                        LoggedUser.isLoggedIn=false
                        if(LoggedUser.isGoogleUser)
                            LoggedUser.isGoogleUser=false
                        navController.navigate("login")

                    }

                )

                Spacer(modifier = Modifier.height(20.dp))
                //        UserProfileInfo(name = "Lorem Ipsum", email = " ", balance = "$0000.00")
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    LoggedUser.isLoggedIn=false
                    if(LoggedUser.isGoogleUser)
                        LoggedUser.isGoogleUser=false
                    LoggedUser.username=""
                    navController.navigate("login")
                }) {
                    Text("Sign out")
                }

            }

        }
    )
}


@Composable
fun ProfileImage(
    imageResId: Int,
    userName: String,
    totalBalance: String,
    onUpdateClick: () -> Unit,
    onDeleteClick: () -> Unit // New parameter for delete button click
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Profile Image",
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = userName,
            style = TextStyle(color = Color.White)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Total Balance: $${"%.2f".format(totalBalance.toDoubleOrNull() ?: 0.0)}",
            style = TextStyle(color = Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onUpdateClick,
            modifier = Modifier.width(IntrinsicSize.Max)
        ) {
            Text("Update User")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = onDeleteClick, // Set onClick listener for delete button
            modifier = Modifier.width(IntrinsicSize.Max)
        ) {
            Text("Delete Account")
        }
    }
}



