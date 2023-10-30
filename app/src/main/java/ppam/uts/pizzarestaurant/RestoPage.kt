package ppam.uts.pizzarestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ppam.uts.pizzarestaurant.databinding.RestoPageBinding

class RestoPage : AppCompatActivity() {
    private var binding: RestoPageBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RestoPageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val selectedStore = intent.getStringExtra("selectedStore")
        val customerName = intent.getStringExtra("customerName")

        binding?.store?.text = selectedStore
        binding?.upBarName?.text = "Hello, $customerName"

        if (selectedStore.equals("cibiru", ignoreCase = true)){
            binding?.mainImage?.setImageResource(R.drawable.img_resto_cibiru)
        } else {
            binding?.mainImage?.setImageResource(R.drawable.img_resto_cihampeulas)
        }

        binding?.showMenuButton?.setOnClickListener {
            val intent = Intent(this,MenuPage::class.java )
            intent.putExtra("selectedStore", selectedStore)
            intent.putExtra("customerName", customerName)

            startActivity(intent)
        }
    }
}