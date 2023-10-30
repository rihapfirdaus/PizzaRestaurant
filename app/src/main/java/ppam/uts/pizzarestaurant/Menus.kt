package ppam.uts.pizzarestaurant

import android.content.Context

object Menus {
    fun getMenuData(context: Context) :ArrayList<MenuModel>{
        val data = ArrayList<MenuModel>()

        data.add(MenuModel(
            "Pepperoni Pizza",
            66000,
            context.getString(R.string.short_desc_pizza),
            context.getString(R.string.long_desc_pizza),
            R.drawable.img_pizza))
        data.add(MenuModel(
            "Spaghetti",
            30000,
            context.getString(R.string.short_desc_spaghetti),
            context.getString(R.string.long_desc_spaghetti),
            R.drawable.img_spageti))
        data.add(
            MenuModel(
                "Burger",
                28000,
                context.getString(R.string.short_desc_burger),
                context.getString(R.string.long_desc_burger),
                R.drawable.img_burger
            )
        )
        data.add(
            MenuModel(
                "Steak",
                70000,
                context.getString(R.string.short_desc_steak),
                context.getString(R.string.long_desc_steak),
                R.drawable.img_steak
            )
        )

        return data
    }
}