package uk.ac.tees.mad.expensetracker.util

import uk.ac.tees.mad.expensetracker.R
import uk.ac.tees.mad.expensetracker.model.CategoryModel

object Constants {
    const val USERS = "users"
    const val EXPENSES = "expenses"
    const val APIKEY = "18055e06d1a4298f7e678e99"

    fun getCurrencyIcon(currency: Int): Int{
        return when(currency){
            1 -> R.drawable.baseline_currency_rupee_24
            2 -> R.drawable.baseline_euro_24
            3 -> R.drawable.baseline_currency_yen_24
            4 -> R.drawable.baseline_currency_pound_24
            7 -> R.drawable.baseline_currency_franc_24
            else -> R.drawable.baseline_attach_money_24
        }
    }

    fun getCategoryInfo(cat: Int): CategoryModel{
        return when(cat){
            1-> CategoryModel("Shopping",1,R.drawable.shopping, MyColors.categoryColor1)
            2-> CategoryModel("Food",2,R.drawable.food, MyColors.categoryColor2)
            3-> CategoryModel("Gifts",3,R.drawable.gift, MyColors.categoryColor3)
            4-> CategoryModel("Subscriptions",4,R.drawable.subscription, MyColors.categoryColor4)
            5-> CategoryModel("Entertainment",5,R.drawable.entertainment, MyColors.categoryColor5)
            else -> CategoryModel("Others",6,R.drawable.others, MyColors.categoryColor6)
        }
    }

    fun getCurrency(curr:Int): String{
        return when(curr){
            1-> "INR"
            2-> "EUR"
            3-> "JPY"
            4-> "GBP"
            5-> "AUD"
            6-> "CAD"
            7-> "CHF"
            else -> "USD"
        }
    }

    fun getCurrencyList(): List<String>{
        return listOf("USD","INR","EUR","JPY","GBP","AUD","CAD","CHF")
    }
}