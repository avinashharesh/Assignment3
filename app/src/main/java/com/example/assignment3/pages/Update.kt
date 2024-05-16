package com.example.assignment3.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.assignment3.LoggedUser
import com.example.assignment3.ui.theme.TopAppBarBackground
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat
import java.util.Calendar


/*
Page to add the expenses
 */



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Update(
    navController: NavController,
    Id: String,
    Description: String,
    Selected_Type: String,
    Amount: Double,
    Category: String
) {

    var id by remember { mutableStateOf(Id) }
    var description by remember { mutableStateOf(TextFieldValue(Description)) }
    var selectedType by remember {
        if (Selected_Type == "Expense")
            mutableStateOf(Selected_type.Expense)
        else
            mutableStateOf(Selected_type.Income)
    }
    var selectedCategory by remember { mutableStateOf(Category) }
    var amount by remember { mutableStateOf(Amount.toString()) }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Update Transaction") },
                modifier = Modifier.padding(top = 20.dp),
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = TopAppBarBackground)
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Amount") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Typeselector(selectedType) { type -> selectedType = type }
                Spacer(modifier = Modifier.height(16.dp))
                //select the category
                ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded=!isExpanded})
                {
                    TextField(modifier = Modifier.menuAnchor(), value = selectedCategory, onValueChange ={}, readOnly = true, trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = isExpanded
                    )} )

                    ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded=false }) {
                        if (selectedType == Selected_type.Expense) {
                            expenseCategories.forEachIndexed { index, text ->
                                DropdownMenuItem(
                                    text = { Text(text = text) },
                                    onClick = {
                                        selectedCategory = expenseCategories[index]
                                        isExpanded = false
                                    },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                )
                            }
                        } else {
                            incomeCategories.forEachIndexed { index, text ->
                                DropdownMenuItem(
                                    text = { Text(text = text) },
                                    onClick = {
                                        selectedCategory = incomeCategories[index]
                                        isExpanded = false
                                    },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (errorMessage != null) {
                    Text(errorMessage!!, color = MaterialTheme.colorScheme.error)
                }
                Button(
                    onClick = {
                        if (amount.isEmpty() || description.text.isEmpty()) {
                            errorMessage = "Amount and Description cannot be empty"
                        } else {
                            // Perform action on Add button click when fields are not empty
                            // Clear error message if any
                            errorMessage = null
                            val db = FirebaseDatabase.getInstance()
                            var reference = db.getReference(LoggedUser.username)
                            // Get today's date
                            val calendar=Calendar.getInstance().time
                            val date=DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar)
                            val time=DateFormat.getTimeInstance().format(calendar)
                            val datetime=time+"  "+date
                            var required_amount=amount.toDouble()
                            if(selectedType.toString()=="Expense")
                                required_amount*=-1
                            val expenseData = hashMapOf<String, Any>(
                                "id" to id.toString(),
                                "description" to description.text,
                                "type" to selectedType.toString(),
                                "category" to selectedCategory.toString(),
                                "amount" to required_amount,
                                "date" to datetime
                            )
                            reference.child(id).updateChildren(expenseData)

                            //updating user data
                            reference=db.getReference("Users")
                            reference.child(LoggedUser.username).get().addOnSuccessListener { snapshot ->
                                if (!snapshot.exists()) {
                                    // Username does not exists, show error message
                                    errorMessage = "Username does not exist"
                                } else {
                                    val userData = snapshot.value as? Map<String, Any>
                                    var totalBalance = userData?.get("totalBalance")?.toString()?.toDoubleOrNull() ?: 0.0
                                    var totalExpense = userData?.get("totalExpense")?.toString()?.toDoubleOrNull() ?: 0.0
                                    var totalIncome = userData?.get("totalIncome")?.toString()?.toDoubleOrNull() ?: 0.0
                                    totalBalance-=Amount
                                    totalBalance += required_amount
                                    if(selectedType.toString()=="Expense") {
                                        totalExpense -= Amount
                                        totalExpense += required_amount
                                    }
                                    else {
                                        totalExpense-=Amount
                                        totalIncome += required_amount
                                    }
                                    val updatedUserData = hashMapOf<String, Any>(
                                        "totalBalance" to totalBalance,
                                        "totalExpense" to totalExpense,
                                        "totalIncome" to totalIncome
                                    )
                                    reference.child(LoggedUser.username).updateChildren(updatedUserData)
                                    navController.navigate("expenses")
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "Update")
                }

                Spacer(modifier = Modifier.height(8.dp)) // Add some space between buttons

                Button(
                    onClick = {
                        // Perform delete action here

                        val db=FirebaseDatabase.getInstance()
                        var reference=db.getReference(LoggedUser.username).child(Id)
                        val delete_task=reference.removeValue()
                        delete_task.addOnSuccessListener {
                            println("Successfully deleted")
                        }.addOnFailureListener{
                            println("Deletion Failed")
                        }

                        reference=db.getReference("Users")
                        reference.child(LoggedUser.username).get().addOnSuccessListener { snapshot ->
                            if (!snapshot.exists()) {
                                // Username does not exists, show error message
                                errorMessage = "Username does not exist"
                            } else {
                                val userData = snapshot.value as? Map<String, Any>
                                var totalBalance = userData?.get("totalBalance")?.toString()?.toDoubleOrNull() ?: 0.0
                                var totalExpense = userData?.get("totalExpense")?.toString()?.toDoubleOrNull() ?: 0.0
                                var totalIncome = userData?.get("totalIncome")?.toString()?.toDoubleOrNull() ?: 0.0
                                totalBalance-=Amount
                                if(selectedType.toString()=="Expense") {
                                    totalExpense -= Amount
                                }
                                else {
                                    totalExpense-=Amount
                                }
                                val updatedUserData = hashMapOf<String, Any>(
                                    "totalBalance" to totalBalance,
                                    "totalExpense" to totalExpense,
                                    "totalIncome" to totalIncome
                                )
                                reference.child(LoggedUser.username).updateChildren(updatedUserData)
                                navController.navigate("expenses")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = "Delete")
                }
            }
        }
    )
}


@Composable
fun Typeselector(selectedType: Selected_type, onTypeSelected: (Selected_type) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Select Type:")
        Spacer(modifier = Modifier.width(16.dp))
        RadioButton(
            selected = selectedType == Selected_type.Expense,
            onClick = { onTypeSelected(Selected_type.Expense) },
            modifier = Modifier.padding(end = 8.dp)
        )
        Text("Expense")
        Spacer(modifier = Modifier.width(16.dp))
        RadioButton(
            selected = selectedType == Selected_type.Income,
            onClick = { onTypeSelected(Selected_type.Income) },
            modifier = Modifier.padding(end = 8.dp)
        )
        Text("Income")
    }
}

enum class Selected_type {
    Expense, Income
}
