package com.example.dundermifflinapp.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.data.models.Order
import com.example.dundermifflinapp.data.models.OrderView

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private var orders: List<OrderView> = listOf()

    fun setOrders(orders: List<OrderView>) {
        this.orders = orders
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = orders[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = orders.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var order: OrderView? = null
        private val customerName: TextView = view.findViewById(R.id.customerName)
        private val orderInfo: LinearLayout = view.findViewById(R.id.orderInfo)
        private val orderItems: TextView = view.findViewById(R.id.orderItems)
        private val orderPurchaseDate: TextView = view.findViewById(R.id.orderPurchaseDate)
        private val orderArrivalDate: TextView = view.findViewById(R.id.orderArrivalDate)
        private val orderValue: TextView = view.findViewById(R.id.orderValue)

        fun bind(order: OrderView) {
            this.order = order
            customerName.text = "${order.customerName}'s order"
            orderItems.text = "${order.items?.size} items"
            orderPurchaseDate.text = "Purchased at: ${order.purchaseDate}"
            orderArrivalDate.text = "Will arrive at: ${order.deliveryDate}"
            orderValue.text = "Total: US$${order.value}"
        }
    }
}