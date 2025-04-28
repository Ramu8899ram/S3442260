package uk.ac.tees.mad.expensetracker.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun ReceiptDialog(
    bitmap: Bitmap?,
    onDismiss:()-> Unit
) {
    Dialog(
        onDismissRequest = {onDismiss()},
    ) {
        Box(
        ) {
            if (bitmap!=null){
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "receipt"
                )
            }
            else{
                Text("No receipt available", fontSize = 22.sp,
                    modifier = Modifier.padding(16.dp))
            }
        }
    }
}