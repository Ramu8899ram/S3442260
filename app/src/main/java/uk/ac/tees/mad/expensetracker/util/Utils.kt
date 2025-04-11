package uk.ac.tees.mad.expensetracker.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {
    fun getDateTimeFromMillis(millis: Long, format: String = "dd/MM/yyyy HH:mm a"): String {
        val date = Date(millis)
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.format(date)
    }
}