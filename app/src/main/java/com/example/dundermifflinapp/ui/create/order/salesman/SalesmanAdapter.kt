package com.example.dundermifflinapp.ui.create.order.salesman

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.data.models.Salesman

class SalesmanAdapter : RecyclerView.Adapter<SalesmanAdapter.ViewHolder>() {

    private var salesman: List<Salesman> = listOf()
    var onItemSelected: ((Any) -> Unit)? = null

    fun setSalesman(salesman: List<Salesman>) {
        this.salesman = salesman
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_select_order, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = salesman[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = salesman.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var salesman: Salesman? = null
        private var itemLabel: TextView = view.findViewById(R.id.itemLabel)
        private var selectButton: LinearLayout = view.findViewById(R.id.selectButton)

        fun bind(salesman: Salesman) {
            this.salesman = salesman
            itemLabel.text = salesman.name
            selectButton.setOnClickListener {
                onItemSelected?.invoke(salesman)
            }
        }
    }

}