package uk.ac.tees.mad.expensetracker.util

import androidx.compose.ui.graphics.Color
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
            1-> CategoryModel("Shopping",100.34,14,"Card",R.drawable.shopping, MyColors.categoryColor1)
            2-> CategoryModel("Food",130.34,15,"Card.Cash",R.drawable.food, MyColors.categoryColor2)
            3-> CategoryModel("Gifts",80.72,12,"Card.Upi",R.drawable.gift, MyColors.categoryColor3)
            4-> CategoryModel("Subscriptions",496.34,20,"Upi",R.drawable.subscription, MyColors.categoryColor4)
            5-> CategoryModel("Entertainment",140.34,16,"Cash",R.drawable.entertainment, MyColors.categoryColor5)
            else -> CategoryModel("Others",600.44,30,"Card",R.drawable.others, MyColors.categoryColor6)
        }
    }
}