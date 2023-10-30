package ppam.uts.pizzarestaurant

data class MenuModel(
    val name: String,
    val price: Int,
    val shortDesc: String,
    val longDesc: String,
    val imageSource: Int
):java.io.Serializable