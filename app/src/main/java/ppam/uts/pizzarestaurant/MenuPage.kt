package ppam.uts.pizzarestaurant

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ppam.uts.pizzarestaurant.databinding.MenuPageBinding

class MenuPage : AppCompatActivity(), ItemAdapter.OnClickListener, ItemAdapter.OnLongClickListener {
    private var customerName: String? = null
    private var selectedStore: String? = null
    private var selectedMenu: String? = null
    private var selectedPrice: Int = 0
    private var binding: MenuPageBinding? = null
    private var selectedItems: Set<MenuModel> = HashSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MenuPageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        customerName = intent.getStringExtra("customerName")
        selectedStore = intent.getStringExtra("selectedStore")

        val menuList = Menus.getMenuData(this)
        binding?.recyclerview?.layoutManager = LinearLayoutManager(this)
        binding?.recyclerview?.setHasFixedSize(true)

        val itemAdapter = ItemAdapter(menuList)
        itemAdapter.setOnClickListener(this) // Set the click listener
        itemAdapter.setOnLongClickListener(this)

        binding?.recyclerview?.adapter = itemAdapter
        binding?.upBarName?.text = "Hello, $customerName"

        binding?.fab?.setOnClickListener {
            if (selectedItems.isNotEmpty()) {
                val intent = Intent(this, OrderDetailsPage::class.java)
                intent.putExtra("selectedStore", selectedStore)
                intent.putExtra("customerName", customerName)
                intent.putExtra("order", selectedMenu)
                intent.putExtra("price", selectedPrice)

                startActivity(intent)
            } else {
                Toast.makeText(this, "Tekan lama pada menu untuk memilih", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Implement the onClick function
    override fun onClick(position: Int, model: MenuModel) {
        val intent = Intent(this, MenuDetailsPage::class.java)
        intent.putExtra("selectedStore", selectedStore)
        intent.putExtra("customerName", customerName)
        intent.putExtra("menuName", model.name)
        intent.putExtra("menuPrice", model.price)
        intent.putExtra("menuImage", model.imageSource)
        intent.putExtra("menuLongdesc", model.longDesc)

        startActivity(intent)
    }

    // Implement the onLongClick function
    override fun onLongClick(position: Int, model: MenuModel) {
        // Tambahkan atau hilangkan item dari daftar yang dipilih saat long click
        if (selectedItems.contains(model)) {
            selectedItems = selectedItems.minus(model)
        } else {
            selectedItems = selectedItems.plus(model)
        }
        selectedMenu = model.name
        selectedPrice = model.price
    }
}
