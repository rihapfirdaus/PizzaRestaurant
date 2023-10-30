package ppam.uts.pizzarestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ppam.uts.pizzarestaurant.databinding.MenuDetailsPageBinding


class MenuDetailsPage : AppCompatActivity() {
    private var binding :MenuDetailsPageBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MenuDetailsPageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val customerName = intent.getStringExtra("customerName")
        val selectedStore = intent.getStringExtra("selectedStore")
        val menuName = intent.getStringExtra("menuName")
        val menuPrice = intent.getIntExtra("menuPrice", 0)
        val menuImage = intent.getIntExtra("menuImage", R.drawable.img_pizza)
        val menuLongDesc = intent.getStringExtra("menuLongdesc")

        binding?.menuDetailsName?.text = menuName
        binding?.menuDetailsInfo?.text = menuLongDesc
        binding?.menuDetailsImage?.setImageResource(menuImage)

        val priceString = "Rp.$menuPrice,00"
        binding?.priceTag?.text = priceString

        binding?.orderMenuButton?.setOnClickListener {
            val intent = Intent(this, OrderDetailsPage::class.java)

            intent.putExtra("selectedStore", selectedStore)
            intent.putExtra("customerName", customerName)
            intent.putExtra("order", menuName)
            intent.putExtra("price", menuPrice)
            startActivity(intent)
        }
        binding?.backButton?.setOnClickListener {
            finish()
        }
    }
}