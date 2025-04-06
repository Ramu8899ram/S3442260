package uk.ac.tees.mad.expensetracker.util

import uk.ac.tees.mad.expensetracker.R
import uk.ac.tees.mad.expensetracker.model.CategoryModel

object Constants {
    fun getCategoryList(): List<CategoryModel>{
        return listOf(
            CategoryModel("Shopping",100.34,14,"Card",R.drawable.shopping, MyColors.categoryColor1),
            CategoryModel("Food",130.34,15,"Card.Cash",R.drawable.food, MyColors.categoryColor2),
            CategoryModel("Gifts",80.72,12,"Card.Upi",R.drawable.gift, MyColors.categoryColor3),
            CategoryModel("Subscriptions",496.34,20,"Upi",R.drawable.subscription, MyColors.categoryColor4),
            CategoryModel("Entertainment",140.34,16,"Cash",R.drawable.entertainment, MyColors.categoryColor5),
            CategoryModel("Others",600.44,30,"Card",R.drawable.others, MyColors.categoryColor6)
        )
    }

    fun getCurrencyIcon(currency: String): Int{
        return when(currency){
            "inr" -> R.drawable.baseline_currency_rupee_24
            "eur" -> R.drawable.baseline_euro_24
            "jpy" -> R.drawable.baseline_currency_yen_24
            "gbp" -> R.drawable.baseline_currency_pound_24
            "chf" -> R.drawable.baseline_currency_franc_24
            else -> R.drawable.baseline_attach_money_24
        }
    }
}