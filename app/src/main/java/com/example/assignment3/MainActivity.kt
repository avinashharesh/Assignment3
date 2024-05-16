package com.example.assignment3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.assignment3.pages.Account
import com.example.assignment3.pages.Add
import com.example.assignment3.pages.Expenses
import com.example.assignment3.pages.Login
import com.example.assignment3.pages.NewsScreen
import com.example.assignment3.pages.Reports
import com.example.assignment3.pages.Signup
import com.example.assignment3.pages.Update
import com.example.assignment3.pages.UpdateUser
import com.example.assignment3.ui.theme.Assignment3Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment3Theme {
                val navController= rememberNavController()
                val backStackEntry=navController.currentBackStackEntryAsState()
                Surface(modifier = Modifier.fillMaxSize()) {

                }

                Scaffold (

                    bottomBar = {
                    if(shouldShowBottomBar(backStackEntry.value?.destination?.route))
                        {
                            NavigationBar {
                                NavigationBarItem( //The icon for expenses home page
                                    selected = backStackEntry.value?.destination?.route == "expenses",//selected if the last clicked value was expenses
                                    onClick = { navController.navigate("expenses") },//on click action
                                    label = {
                                        Text(text = "Home")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.home_24dp_fill0_wght400_grad0_opsz24),//linking to the xml icon in resources
                                            contentDescription = "Upload",
                                            tint= Color.White
                                        )
                                    }
                                )
                                NavigationBarItem( //The icon for report page
                                    selected = backStackEntry.value?.destination?.route == "reports",//selected if the last clicked value was report
                                    onClick = { navController.navigate("reports") },//on click action
                                    label = {
                                        Text(text = "Reports")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.bar_chart),//linking to the xml icon in resources
                                            contentDescription = "Reports",
                                            tint= Color.White
                                        )
                                    }
                                )
                                NavigationBarItem( //The icon for Add page
                                    selected = backStackEntry.value?.destination?.route == "add",//selected if the last clicked value was add expenses
                                    onClick = { navController.navigate("add") },//on click action
                                    label = {
                                        Text(text = "Add")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.add),//linking to the xml icon in resources
                                            contentDescription = "Add",
                                            tint= Color.White
                                        )
                                    }
                                )
                                NavigationBarItem( //The icon for Profile page
                                    selected = backStackEntry.value?.destination?.route == "account",//selected if the last clicked value was profile page
                                    onClick = { navController.navigate("account") },//on click action
                                    label = {
                                        Text(text = "Account")
                                    },
                                    icon = {
                                        Icon(
                                            painterResource(id = R.drawable.account_circle),//linking to the xml icon in resources
                                            contentDescription = "Account",
                                            tint= Color.White
                                        )
                                    }
                                )
                            }
                        }

                    },
                    content = { innerPadding ->
                        NavHost(navController = navController, startDestination = "login", builder = {
                            composable("login",){
                                Login(navController)
                            }
                            composable("signup",){
                                Signup(navController)
                            }
                            composable("expenses",){
                                Expenses(navController)
                            }
                            composable("add"){
                                Add(navController)
                            }
                            composable("reports"){
                                Reports(navController)
                            }
                            composable("account"){
                                Account(navController)
                            }
                            composable("newsScreen"){
                                NewsScreen(navController)
                            }
                            composable("updateuser/{fullname}/{totalbalance}/{email}/{password}"){
                                val fullname=it.arguments?.getString("fullname")
                                val totalbalance=it.arguments?.getString("totalbalance")
                                val email=it.arguments?.getString("email")
                                val password=it.arguments?.getString("password")
                                if(fullname!=null && totalbalance!=null && email!=null && password!=null)
                                    UpdateUser(navController,fullname,totalbalance.toDouble(),email,password)
                                else
                                    UpdateUser(navController,"",0.0,"","")
                            }
                            composable("update/{id}/{description}/{type}/{amount}/{category}"){
                                val id= it.arguments?.getString("id")
                                val description=it.arguments?.getString("description")
                                val type=it.arguments?.getString("type")
                                val amount=it.arguments?.getString("amount")
                                val category=it.arguments?.getString("category")
                                if (id != null && description!=null && type!=null && amount!=null && category!=null) {
                                    Update(
                                        navController =navController ,
                                        Id = id,
                                        Description = description,
                                        Selected_Type = type ,
                                        Amount = amount.toDouble(),
                                        Category = category
                                    )
                                }
                                else
                                {
                                    Update(
                                        navController = navController,
                                        Id = "",
                                        Description = "",
                                        Selected_Type = "",
                                        Amount = 0.0,
                                        Category =""
                                    )
                                }
                            }


                        })
                    }
                )
            }

        }

    }

    private fun shouldShowBottomBar(route: String?): Boolean {
        return !(route == "login" || route == "signup")

    }
}




