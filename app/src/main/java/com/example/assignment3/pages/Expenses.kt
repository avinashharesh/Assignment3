package com.example.assignment3.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.assignment3.LoggedUser
import com.example.assignment3.R
import com.example.assignment3.ui.theme.TopAppBarBackground
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Locale


/*
Page to view expenses, also set as the default page to land on after log-in
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Expenses(navController: NavController) {
    var totalBalance by remember { mutableStateOf(0.0) }
    var totalIncome by remember { mutableStateOf(0.0) }
    var totalExpense by remember { mutableStateOf(0.0) }
    var expensesList by remember { mutableStateOf(mutableListOf<Transaction>()) }
    LaunchedEffect(Unit){
        val db = FirebaseDatabase.getInstance()
        var reference = db.getReference("Users")
        reference.child(LoggedUser.username).get().addOnSuccessListener { snapshot ->
            if (!snapshot.exists()) {
                // Username does not exist, show error message
            } else {
                val userData = snapshot.value as? Map<String, Any>
                totalBalance = userData?.get("totalBalance")?.toString()?.toDoubleOrNull() ?: 0.0
                totalExpense = userData?.get("totalExpense")?.toString()?.toDoubleOrNull() ?: 0.0
                totalIncome = userData?.get("totalIncome")?.toString()?.toDoubleOrNull() ?: 0.0
            }
        }

        reference=db.getReference(LoggedUser.username)
        reference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val tempList = mutableListOf<Transaction>()  // Temporary list for sorting
                for(expenseSnapshot in snapshot.children) {
                    val id = expenseSnapshot.child("id").getValue(String::class.java)
                    val date = expenseSnapshot.child("date").getValue(String::class.java)
                    val description = expenseSnapshot.child("description").getValue(String::class.java)
                    val type = expenseSnapshot.child("type").getValue(String::class.java)
                    val amount = expenseSnapshot.child("amount").getValue(Double::class.java)
                    val category = expenseSnapshot.child("category").getValue(String::class.java)
                    val expense = Transaction(id, date, description, type, amount, category)
                    tempList.add(expense)
                }
                // Sort tempList based on date in descending order
                expensesList = tempList.sortedByDescending { expense ->
                    val dateFormat = SimpleDateFormat("hh:mm:ss a  dd/MM/yy", Locale.getDefault())
                    dateFormat.parse(expense.date)
                }.toMutableList()
            }
            override fun onCancelled(error: DatabaseError) {
                println("Failed to load the database")
            }
        })
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Home") },
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                actions = {
                    // News icon on the top right
                    IconButton(onClick = {
                        // Assume 'navController' can navigate to a 'NewsScreen' that you've set up
                        navController.navigate("newsScreen")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.newspaper), // Ensure you have 'ic_news.xml' in your drawable folder
                            contentDescription = "View Financial News"
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = TopAppBarBackground)
            )
        },
        content = { innerPadding ->
            Text(
                text = "Welcome to Expense Echo!",
                modifier = Modifier.fillMaxWidth().padding(top = 80.dp),
                textAlign = TextAlign.Center)
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                // Display Total Balance, Income, and Expense in a single Card
                item {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.cardElevation(8.dp) // Setting elevation to 8.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp) // Add bottom padding to increase gap
                        ) {
                            Text(
                                text = "Total Balance: $${"%.2f".format(totalBalance)}",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(16.dp)) // Add vertical space between Total Balance and Income/Expense
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Total Income: $${"%.2f".format(totalIncome)}",
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.Start,
                                    color = androidx.compose.ui.graphics.Color.Green // Setting color to green for income
                                )
                                Text(
                                    text = "Total Expense: $${"%.2f".format(totalExpense)}",
                                    modifier = Modifier.weight(1f),
                                    textAlign = TextAlign.End,
                                    color = androidx.compose.ui.graphics.Color.Red // Setting color to red for expense
                                )
                            }
                        }
                    }
                }

                // Add a button for adding expense/income
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                    ) {
                        Button(
                            onClick = {
                                // Handle button click, navigate to add expense/income screen
                                      navController.navigate("add")
                            },
                            modifier = Modifier.fillMaxWidth() .height(50.dp),
                        ) {
                            Text(text = "Add Expense/Income")
                        }
                    }
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        if (expensesList.isEmpty()) {
                            Text(
                                text = "No Expenses/Income",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        } else {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                for (index in expensesList.indices) {
                                    val expense = expensesList[index]
                                    ExpenseCard(navController = navController, expense = expense)
                                }
                            }
                        }
                    }
                }



            }
        }
    )

}


@Composable
fun ExpenseCard(navController: NavController, expense: Transaction) {
    Column(
        modifier = Modifier
            .clickable {
                // Define navigation route with parameters
                navController.navigate("update/${expense.id}/${expense.description}/${expense.type}/${expense.amount.toString()}/${expense.category}")
            }
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .fillMaxWidth()
            // Additional padding at the bottom to avoid being covered by the navigation bar
            .padding(bottom = 16.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),  // Additional padding to space out cards
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(all = 16.dp)
            ) {
                // Conditional icon based on transaction type
                Icon(
                    painter = painterResource(id = if (expense.type == "Expense") R.drawable.arrow_downward else R.drawable.arrow_upward),
                    contentDescription = if (expense.type == "Expense") "Expense Icon" else "Income Icon",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(text = "Description: ${expense.description}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Created: ${expense.date}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Amount: $${expense.amount}")
                }
            }
        }

    }
}


data class Transaction(
    val id: String? = null,
    val date: String? = null,
    val description: String? = null,
    val type: String? = null,
    val amount: Double? = null,
    val category: String? = null
)




