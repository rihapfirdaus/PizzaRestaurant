package ppam.uts.pizzarestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ppam.uts.pizzarestaurant.databinding.MenuCardBinding

class ItemAdapter(private val items: List<MenuModel>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private var onClickListener: OnClickListener? = null
    private var onLongClickListener: OnLongClickListener? = null
    private var selectedPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MenuCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.menuImage.setImageResource(item.imageSource)
        holder.menuLabel.text = item.name
        holder.menuDesc.text = item.shortDesc

        val isItemSelected = position == selectedPosition
        if (isItemSelected) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.selectedColor))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, android.R.color.transparent))
        }

        holder.itemView.setOnClickListener {
            if (onClickListener != null && position != selectedPosition) {
                onClickListener!!.onClick(position, item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    fun setOnLongClickListener(onLongClickListener: OnLongClickListener) {
        this.onLongClickListener = onLongClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: MenuModel)
    }

    interface OnLongClickListener {
        fun onLongClick(position: Int, model: MenuModel)
    }

    inner class ViewHolder(binding: MenuCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val menuImage = binding.menuImage
        val menuLabel = binding.menuLabel
        val menuDesc = binding.menuShortDesc

        init {
            binding.root.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    selectedPosition = position
                    notifyDataSetChanged()
                    onLongClickListener?.onLongClick(position, items[position])
                }
                true
            }
        }
    }
}
