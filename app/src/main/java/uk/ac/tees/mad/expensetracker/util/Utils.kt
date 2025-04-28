package uk.ac.tees.mad.expensetracker.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.core.graphics.scale
import uk.ac.tees.mad.expensetracker.data.local.roomdb.ExpenseEntity
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.github.mikephil.charting.data.BarEntry
import com.google.gson.Gson
import uk.ac.tees.mad.expensetracker.model.CurrencyResponse


object Utils {
    fun getDateTimeFromMillis(millis: Long, format: String = "dd/MM/yyyy HH:mm a"): String {
        val date = Date(millis)
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.format(date)
    }

    fun bitmapToBase64(
        bitmap: Bitmap,
        maxWidth: Int = 800,
        maxHeight: Int = 800,
        quality: Int = 50
    ): String? {
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

    fun prepareChartData(
        expenses: List<ExpenseEntity>,
        currencyRate: CurrencyResponse,
        selectedCurr: String
    ): Pair<List<BarEntry>, List<String>> {
        val groupedExpenses = expenses
            .groupBy { it.time / (24 * 60 * 60 * 1000) }
            .mapValues { entry ->
                entry.value.sumOf {
                    exchange(
                        currencyRate.conversion_rates[Constants.getCurrency(it.currency)]?:1.0,
                        currencyRate.conversion_rates[selectedCurr]?:1.0,
                        it.amount
                    )
                }
            } // Sum expenses per day
            .toSortedMap()

        val entries = mutableListOf<BarEntry>()
        val labels = mutableListOf<String>()

        groupedExpenses.entries.forEachIndexed { index, (day, totalAmount) ->
            entries.add(BarEntry(index.toFloat(), totalAmount.toFloat()))

            val date = SimpleDateFormat(
                "MMM dd",
                Locale.getDefault()
            ).format(Date(day * 24 * 60 * 60 * 1000))
            labels.add(date)
        }

        return Pair(entries, labels)
    }

    fun getDate(timeMillis:Long):String{
        return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(timeMillis))
    }

    fun getExpenseSum(list: List<ExpenseEntity>, currencyRate: CurrencyResponse, selectedCurr: String): Double {
        return list.sumOf {
            exchange(
                currencyRate.conversion_rates[Constants.getCurrency(it.currency)]?:1.0,
                currencyRate.conversion_rates[selectedCurr]?:1.0,
                it.amount
            )
        }
    }

    fun exchange(from: Double, to: Double, value: Double): Double {
        return (value / from) * to
    }

    val gson = Gson()
    fun objectToJson(response: CurrencyResponse): String {

        return gson.toJson(response)
    }

    fun jsonToObject(json: String): CurrencyResponse {
        return gson.fromJson(json, CurrencyResponse::class.java)
    }
}