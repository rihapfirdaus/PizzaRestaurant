package ppam.uts.pizzarestaurant

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ppam.uts.pizzarestaurant.databinding.WelcomePageBinding

class WelcomePage : AppCompatActivity() {
    private var binding: WelcomePageBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomePageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.submitButton?.setOnClickListener {
            val selectedStore = binding?.dropdownStore?.selectedItem.toString()
            val name = binding?.inputName?.text.toString()

            if (selectedStore.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "Isi form terlebih dahulu", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, RestoPage::class.java)
                intent.putExtra("selectedStore", selectedStore)
                intent.putExtra("customerName", name)
                startActivity(intent)
            }
        }
    }
}