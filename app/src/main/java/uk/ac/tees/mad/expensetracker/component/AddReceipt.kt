package uk.ac.tees.mad.expensetracker.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.expensetracker.R

@Composable
fun AddReceipt() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Receipt Image:", fontSize = 22.sp)
        Spacer(Modifier.height(8.dp))
        Image(
            painter = painterResource(R.drawable.add_photo),
            contentDescription = "image",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.Gray),
            modifier = Modifier.width(80.dp)
                .height(130.dp)
                .border(1.dp, color = Color.Gray , RectangleShape)
        )
    }
}