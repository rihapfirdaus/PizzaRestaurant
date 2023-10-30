package ppam.uts.pizzarestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import ppam.uts.pizzarestaurant.databinding.OrderDetailsPageBinding

class OrderDetailsPage : AppCompatActivity() {
    private var binding: OrderDetailsPageBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OrderDetailsPageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val customerName = intent.getStringExtra("customerName")
        val selectedStore = intent.getStringExtra("selectedStore")
        val orderName = intent.getStringExtra("order")
        val orderPrice = intent.getIntExtra("price", 0)

        binding?.userText?.text = customerName
        binding?.storeText?.text = selectedStore
        binding?.orderText?.text = orderName

        val priceString = "Rp.$orderPrice,00"
        binding?.priceTag?.text = priceString

        val takeawayCheck = binding?.takeawayRadio
        val deliveryCheck = binding?.deliveryRadio
        val doneButton = binding?.doneButton
        val orderMessage = binding?.orderMessage
        val backToDashboard = binding?.backhomeButton

        doneButton?.setOnClickListener {
            if (takeawayCheck?.isChecked == true || deliveryCheck?.isChecked == true){
                var message: String

                if (takeawayCheck?.isChecked == true) {
                    message = getString(R.string.message_take_away_format, customerName, selectedStore, orderName)
                } else {
                    message = getString(R.string.message_delivery_format, customerName, selectedStore, orderName)
                }

                takeawayCheck?.isEnabled = false
                deliveryCheck?.isEnabled = false
                orderMessage?.text = message
                orderMessage?.visibility = View.VISIBLE
                doneButton?.visibility = View.GONE
                backToDashboard?.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "Anda belum memilih metode pengiriman", Toast.LENGTH_SHORT).show()
            }
        }

        backToDashboard?.setOnClickListener {
            val intent = Intent(this, WelcomePage::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

    }
}
