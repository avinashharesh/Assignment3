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

val expenseCategories = listOf("Shopping", "Food", "Bills", "Rent", "Healthcare", "Entertainment", "Education", "Loan", "Investment", "Other")
val incomeCategories = listOf("Salary", "Freelance", "Rental", "Business", "Gifts", "Other")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Add(navController: NavController) {

    var id by remember { mutableStateOf("") }
    var description by remember { mutableStateOf(TextFieldValue()) }
    var selectedType by remember { mutableStateOf(Type.Expense) }
    var selectedCategory by remember {
        mutableStateOf("Select Category")
    }
    var amount by remember { mutableStateOf("") }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Add Transaction") },
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
                TypeSelector(selectedType) { type -> selectedType = type }
                Spacer(modifier = Modifier.height(16.dp))
                //select the category
                ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded=!isExpanded})
                {
                    TextField(modifier = Modifier.menuAnchor().width(380.dp), value = selectedCategory, onValueChange ={}, readOnly = true, trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = isExpanded
                    )} )

                    ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded=false }) {
                        if (selectedType == Type.Expense) {
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
                        }
                        else if((selectedCategory.equals("Select Category"))){
                            errorMessage="Select a category"
                        }
                        else {
                            // Perform action on Add button click when fields are not empty
                            // Clear error message if any
                            errorMessage = null
                            val db = FirebaseDatabase.getInstance()
                            var reference = db.getReference(LoggedUser.username)
                            id= encryptString(LoggedUser.username+description.text+selectedType.toString()+selectedCategory.toString()+amount.toString())
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
                            reference.child(id).setValue(expenseData)

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
                                    totalBalance += required_amount
                                    if(selectedType.toString()=="Expense")
                                        totalExpense+=required_amount
                                    else
                                        totalIncome+=required_amount
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
                    modifier = Modifier.fillMaxWidth() .height(55.dp),
                ) {
                    Text(text = "Add")
                }
            }
        }
    )
}

@Composable
fun TypeSelector(selectedType: Type, onTypeSelected: (Type) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Select Type:")
        Spacer(modifier = Modifier.width(16.dp))
        RadioButton(
            selected = selectedType == Type.Expense,
            onClick = { onTypeSelected(Type.Expense) },
            modifier = Modifier.padding(end = 8.dp)
        )
        Text("Expense")
        Spacer(modifier = Modifier.width(16.dp))
        RadioButton(
            selected = selectedType == Type.Income,
            onClick = { onTypeSelected(Type.Income) },
            modifier = Modifier.padding(end = 8.dp)
        )
        Text("Income")
    }
}

fun encryptString(input: String): String {
    val alphabet = ('a'..'z').toList()
    val shuffledAlphabet = alphabet.shuffled()
    val encryptionMap = alphabet.zip(shuffledAlphabet).toMap()

    return input.map { char ->
        if (char in alphabet) {
            encryptionMap[char] ?: char
        } else {
            'a'
        }
    }.joinToString("")
}

enum class Type {
    Expense, Income
}
