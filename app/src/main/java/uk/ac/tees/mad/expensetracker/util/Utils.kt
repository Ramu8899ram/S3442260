package uk.ac.tees.mad.expensetracker.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.core.graphics.scale
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {
    fun getDateTimeFromMillis(millis: Long, format: String = "dd/MM/yyyy HH:mm a"): String {
        val date = Date(millis)
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.format(date)
    }

    fun bitmapToBase64(bitmap: Bitmap, maxWidth: Int = 800, maxHeight: Int = 800, quality: Int = 50): String? {
        return try {

            val resizedBitmap = bitmap.scale(maxWidth, maxHeight)

            val outputStream = ByteArrayOutputStream()
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
            val byteArray = outputStream.toByteArray()

            Base64.encodeToString(byteArray, Base64.DEFAULT)
        } catch (e: Exception) {
            Log.e("BitmapToBase64", "Error: ${e.message}")
            null
        }
    }

    fun base64ToBitmap(base64String: String): Bitmap? {
        return try {
            val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: Exception) {
            Log.e("base64ToBitmap", e.message.toString())
            null
        }
    }
}