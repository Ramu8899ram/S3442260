package uk.ac.tees.mad.expensetracker.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddNotes(
    text: String,
    onValueChange:(String)-> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Note:", fontSize = 22.sp)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = text,
            onValueChange = {onValueChange(it)},
            placeholder = {Text("Note..")},
            modifier = Modifier.fillMaxWidth()
        )
    }
}