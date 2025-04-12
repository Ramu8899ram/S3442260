package uk.ac.tees.mad.expensetracker.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.expensetracker.R

@Composable
fun AddReceipt(
    bitmap: Bitmap?,
    onClick:()-> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Receipt Image:", fontSize = 22.sp)
        Spacer(Modifier.height(8.dp))
        if (bitmap==null) {
            Image(
                painter = painterResource(R.drawable.add_photo),
                contentDescription = "image",
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Color.Gray),
                modifier = Modifier.width(80.dp)
                    .height(130.dp)
                    .border(1.dp, color = Color.Gray , RectangleShape)
                    .clickable{
                        onClick()
                    }
            )
        }
        else{
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "image",
                contentScale = ContentScale.Fit,
                modifier = Modifier.width(80.dp)
                    .height(130.dp)
                    .border(1.dp, color = Color.Gray , RectangleShape)
                    .clickable{
                        onClick()
                    }
            )
        }
    }
}