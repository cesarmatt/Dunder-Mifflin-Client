package com.example.dundermifflinapp.ui.create.order.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.data.models.OrderItem

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    private var items: List<OrderItem> = listOf()
    var onItemSelected: ((Any) -> Unit)? = null

    fun setItems(items: List<OrderItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_select_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var item: OrderItem? = null
        private var itemLabel: TextView = view.findViewById(R.id.itemLabel)
        private var selectButton: LinearLayout = view.findViewById(R.id.selectButton)

        fun bind(item: OrderItem) {
            this.item = item
            itemLabel.text = item.name
            selectButton.setOnClickListener {
                onItemSelected?.invoke(item)
            }
        }
    }
}