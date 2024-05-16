package com.example.assignment3.pages

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.assignment3.ui.theme.TopAppBarBackground
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView
import com.example.assignment3.LoggedUser
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter




/*
Page to view the expenses in graph format, basically a report of the expenses
 */


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Reports(navController: NavController) {
    var expensecategoryTotalMap by remember { mutableStateOf<Map<String, Double>?>(null) }
    var incomecategoryTotalMap by remember { mutableStateOf<Map<String, Double>?>(null) }
    var isPieChartSelected by remember { mutableStateOf(true) }
    var isExpenseSelected by remember { mutableStateOf(true) } // Track whether PieChart is selected

    // Function to fetch data from Firebase
    LaunchedEffect(Unit) {
        val db = FirebaseDatabase.getInstance()
        val reference = db.getReference(LoggedUser.username)
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<Transaction>()
                val expensefetchedCategoryTotalMap = mutableMapOf<String, Double>()
                val incomefetchedCategoryTotalMap = mutableMapOf<String, Double>()
                for (expenseSnapshot in snapshot.children) {
                    val id = expenseSnapshot.child("id").getValue(String::class.java)
                    val date = expenseSnapshot.child("date").getValue(String::class.java)
                    val description = expenseSnapshot.child("description").getValue(String::class.java)
                    val type = expenseSnapshot.child("type").getValue(String::class.java)
                    val amount = expenseSnapshot.child("amount").getValue(Double::class.java)
                    val category = expenseSnapshot.child("category").getValue(String::class.java)
                    val expense = Transaction(id, date, description, type, amount, category)
                    list.add(expense)

                    // Update categoryTotalMap
                    if (category != null && amount != null && type!=null) {
                        if(type.toString().equals("Expense"))
                        {
                            val currentAmount = expensefetchedCategoryTotalMap[category] ?: 0.0
                            expensefetchedCategoryTotalMap[category] = currentAmount + amount
                        }
                        else
                        {
                            val currentAmount = incomefetchedCategoryTotalMap[category] ?: 0.0
                            incomefetchedCategoryTotalMap[category] = currentAmount + amount
                        }

                    }
                }
                expensecategoryTotalMap=expensefetchedCategoryTotalMap
                incomecategoryTotalMap=incomefetchedCategoryTotalMap
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to load the database")
            }
        })
    }

    // Function to toggle between PieChart and BarChart
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Transaction Report") },
                modifier = Modifier.padding(top = 20.dp),
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = TopAppBarBackground)
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    // Expense and Income Toggle Buttons
                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = { isExpenseSelected = true },
                        enabled = !isExpenseSelected,
                        modifier = Modifier.weight(2f)
                    ) {
                        Text("Expenses",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = { isExpenseSelected = false },
                        enabled = isExpenseSelected,
                        modifier = Modifier.weight(2f)
                    ) {
                        Text("Income",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(17.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Spacer(modifier = Modifier.weight(1f))
                    // Pie Chart and Bar Graph Toggle Buttons
                    Button(
                        onClick = { isPieChartSelected = true },
                        enabled = !isPieChartSelected,
                        modifier = Modifier.weight(2f)
                    ) {
                        Text("Pie Chart",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center)
                    }
                    Spacer(modifier = Modifier.width(17.dp))
                    Button(
                        onClick = { isPieChartSelected = false },
                        enabled = isPieChartSelected,
                        modifier = Modifier.weight(2f)
                    ) {
                        Text("Bar Graph",
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
                // Depending on selection, display PieChart or BarChart
                if (isExpenseSelected) {
                    if (isPieChartSelected) {
                        if (expensecategoryTotalMap != null)
                            PieChartScreen(expensecategoryTotalMap!!, "Expense")
                    } else {
                        if (expensecategoryTotalMap != null)
                            BarChartScreen(expensecategoryTotalMap!!, "Expense")
                    }
                } else {
                    if (isPieChartSelected) {
                        if (incomecategoryTotalMap != null)
                            PieChartScreen(incomecategoryTotalMap!!, "Income")
                    } else {
                        if (incomecategoryTotalMap != null)
                            BarChartScreen(incomecategoryTotalMap!!, "Income")
                    }
                }
            }
        }
    )
}



@Composable
fun PieChartScreen(categoryTotalMap: Map<String, Double>, transaction_type: String) {
    var pieEntries: List<PieEntry>? = null
    if (transaction_type == "Expense") {
        val categories = mapOf(
            "Shopping" to categoryTotalMap.getOrDefault("Shopping", 0.0).toFloat(),
            "Food" to categoryTotalMap.getOrDefault("Food", 0.0).toFloat(),
            "Bills" to categoryTotalMap.getOrDefault("Bills", 0.0).toFloat(),
            "Rent" to categoryTotalMap.getOrDefault("Rent", 0.0).toFloat(),
            "Healthcare" to categoryTotalMap.getOrDefault("Healthcare", 0.0).toFloat(),
            "Entertainment" to categoryTotalMap.getOrDefault("Entertainment", 0.0).toFloat(),
            "Education" to categoryTotalMap.getOrDefault("Education", 0.0).toFloat(),
            "Loan" to categoryTotalMap.getOrDefault("Loan", 0.0).toFloat(),
            "Investment" to categoryTotalMap.getOrDefault("Investment", 0.0).toFloat(),
            "Other" to categoryTotalMap.getOrDefault("Other", 0.0).toFloat()
        ).mapValues { entry -> if (entry.value < 0) -entry.value else entry.value }

        val total = categories.values.sum()
        pieEntries = categories.mapNotNull { (category, amount) ->
            val percentage = (amount / total) * 100
            if (percentage > 0) PieEntry(percentage, category) else null
        }
    } else {
        val categories = mapOf(
            "Salary" to categoryTotalMap.getOrDefault("Salary", 0.0).toFloat(),
            "Freelance" to categoryTotalMap.getOrDefault("Freelance", 0.0).toFloat(),
            "Rental" to categoryTotalMap.getOrDefault("Rental", 0.0).toFloat(),
            "Business" to categoryTotalMap.getOrDefault("Business", 0.0).toFloat(),
            "Gifts" to categoryTotalMap.getOrDefault("Gifts", 0.0).toFloat(),
            "Other" to categoryTotalMap.getOrDefault("Other", 0.0).toFloat()
        ).mapValues { entry -> entry.value }

        val total = categories.values.sum()
        pieEntries = categories.mapNotNull { (category, amount) ->
            val percentage = (amount / total) * 100
            if (percentage > 0) PieEntry(percentage, category) else null
        }
    }

    val pieDataSet = PieDataSet(pieEntries, "Pie Data Set")
    pieDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
    val pieData = PieData(pieDataSet)
    pieDataSet.xValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
    pieDataSet.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE
    pieDataSet.valueFormatter = PercentValueFormatter()
    pieDataSet.valueTextSize = 40f

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            PieChart(context).apply {
                data = pieData
                description.isEnabled = false
                centerText = if (transaction_type == "Expense") "Expense" else "Income"
                setDrawCenterText(true)
                setEntryLabelTextSize(14f)
                animateY(4000)
            }
        }
    )
}


@Composable
fun BarChartScreen(categoryTotalMap: Map<String, Double>, transaction_type: String) {
    val context = LocalContext.current

    if (transaction_type == "Expense") {
        val categories = listOf("Shopping", "Food", "Bills", "Rent", "Healthcare", "Entertainment", "Education", "Loan", "Investment", "Other")
        val values = categories.map { category ->
            categoryTotalMap.getOrDefault(category, 0.0).toFloat().let { if (it < 0) -it else it }
        }

        val barEntries = values.mapIndexed { index, value ->
            BarEntry(index.toFloat(), value)
        }
        val barDataSet = BarDataSet(barEntries, "Expense Categories")
        barDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val barData = BarData(barDataSet).apply {
            barWidth = 0.9f // slightly less than 1 to space bars a bit
        }

        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(top = 50.dp),
            factory = { context ->
                BarChart(context).apply {
                    data = barData
                    description.isEnabled = false
                    setFitBars(true)
                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    xAxis.valueFormatter = IndexAxisValueFormatter(categories)
                    xAxis.textColor = Color.WHITE
                    xAxis.granularity = 1f
                    xAxis.setDrawGridLines(false)
                    animateY(4000)

                    legend.isEnabled = true
                    legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                    legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                    legend.orientation = Legend.LegendOrientation.HORIZONTAL
                    legend.setDrawInside(false)
                    legend.textColor = Color.WHITE
                }
            }
        )
    } else {
        val categories = listOf("Salary", "Freelance", "Rental", "Business", "Gifts", "Other")
        val values = categories.map { category ->
            categoryTotalMap.getOrDefault(category, 0.0).toFloat()
        }

        val barEntries = values.mapIndexed { index, value ->
            BarEntry(index.toFloat(), value)
        }
        val barDataSet = BarDataSet(barEntries, "Income Categories")
        barDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val barData = BarData(barDataSet).apply {
            barWidth = 0.9f // slightly less than 1 to space bars a bit
        }

        AndroidView(
            modifier = Modifier.fillMaxWidth()
                .height(400.dp)
                .padding(top = 50.dp),
            factory = { context ->
                BarChart(context).apply {
                    data = barData
                    description.isEnabled = false
                    setFitBars(true)
                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    xAxis.valueFormatter = IndexAxisValueFormatter(categories)
                    xAxis.textColor = Color.WHITE
                    xAxis.granularity = 1f
                    xAxis.setDrawGridLines(false)
                    animateY(4000)

                    legend.isEnabled = true
                    legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                    legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                    legend.orientation = Legend.LegendOrientation.HORIZONTAL
                    legend.setDrawInside(false)
                    legend.textColor = Color.WHITE
                }
            }
        )
    }
}



//we used this class for formatting value (adding % sign)
class PercentValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        //you can create your own formatting style below
        return "${value.toInt()}%"
    }
}



