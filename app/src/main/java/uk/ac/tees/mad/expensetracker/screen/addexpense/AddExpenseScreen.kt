package uk.ac.tees.mad.expensetracker.screen.addexpense

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uk.ac.tees.mad.expensetracker.component.AddDate
import uk.ac.tees.mad.expensetracker.component.AddNotes
import uk.ac.tees.mad.expensetracker.component.ExpenseAmountRow
import uk.ac.tees.mad.expensetracker.component.PaymentModeSelector
import uk.ac.tees.mad.expensetracker.component.CategorySelector
import uk.ac.tees.mad.expensetracker.component.AddReceipt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(
    navController: NavController,
    viewModel: AddExpenseViewModel = hiltViewModel()
) {
    val selectedPaymentMode = rememberSaveable { mutableIntStateOf(0) }
    val selectedCategory = rememberSaveable { mutableIntStateOf(0) }
    val note = rememberSaveable { mutableStateOf("") }
    val amount = rememberSaveable { mutableStateOf("") }
    val selectedCurrency by viewModel.selectedCurrency.collectAsState()
    val receipt = rememberSaveable { mutableStateOf<Bitmap?>(null) }
    var date by remember { mutableLongStateOf(System.currentTimeMillis()) }
    var showDatePicker by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            receipt.value = bitmap
        }
    }

    Scaffold(
        topBar = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth()
            ){
                Text(
                    "Add Expense",
                    fontSize = 26.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { paddingValues->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
        ){
            ExpenseAmountRow(
                selectedCurrency,
                onAmountChange = {amount.value = it},
                onCurrencyChange = {viewModel.onCurrencyChange(it)}
            )
            PaymentModeSelector(
                {selectedPaymentMode.intValue = it},
                selectedPaymentMode.intValue
            )
            CategorySelector(
                {selectedCategory.intValue = it},
                selectedCategory.intValue
            )
            AddNotes(note.value) {
                note.value = it
            }
            Row {
                AddReceipt(
                    receipt.value,
                    onClick = {
                        if (ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.CAMERA
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            Toast.makeText(
                                context,
                                "Permission denied for camera",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            cameraLauncher.launch()
                        }
                    }
                )
                Spacer(Modifier.weight(1f))
                AddDate(date) {
                    showDatePicker = true
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button({
                viewModel.addExpense(
                    amount = amount.value,
                    currency = selectedCurrency,
                    pMode = selectedPaymentMode.intValue,
                    category = selectedCategory.intValue,
                    note = note.value,
                    image = receipt.value,
                    date = date,
                    context = context
                ){navController.popBackStack()}
            }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text("Add", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("CANCEL")
                }
            }
        ) {
            val datePickerState = rememberDatePickerState()
            DatePicker(
                state = datePickerState
            )

            LaunchedEffect(datePickerState.selectedDateMillis) {
                datePickerState.selectedDateMillis?.let { millis ->
                    date = millis
                }
            }
        }
    }
}