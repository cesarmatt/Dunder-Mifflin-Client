package com.example.dundermifflinapp.ui.create.order.customers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.data.models.Customer
import com.example.dundermifflinapp.data.models.Salesman

class CustomerAdapter : RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    private var customers: List<Customer> = listOf()
    var onItemSelected: ((Any) -> Unit)? = null

    fun setCustomers(customers: List<Customer>) {
        this.customers = customers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_select_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomerAdapter.ViewHolder, position: Int) {
        val item = customers[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = customers.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var customer: Customer? = null
        private var itemLabel: TextView = view.findViewById(R.id.itemLabel)
        private var selectButton: LinearLayout = view.findViewById(R.id.selectButton)

        fun bind(customer: Customer) {
            this.customer = customer
            itemLabel.text = customer.name
            selectButton.setOnClickListener {
                onItemSelected?.invoke(customer)
            }
        }
    }
}