package com.example.dundermifflinapp.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dundermifflinapp.data.feed.FeedRepository
import com.example.dundermifflinapp.data.models.Order
import com.example.dundermifflinapp.data.models.OrderView
import kotlinx.coroutines.launch
import com.example.dundermifflinapp.utils.Result

class FeedViewModel(private val repository: FeedRepository) : ViewModel() {

    private val _orderViews = MutableLiveData<List<OrderView>>()
    val orderViews: LiveData<List<OrderView>>
        get() = _orderViews


    private lateinit var orders: List<Order>

    init {
        getOrders()
        loadInternalData()
    }

    fun getOrders() {
        viewModelScope.launch {
            val response = repository.getOrders()
            if (response is Result.Success) {
                orders = response.data
                getViews(orders)
            }
        }
    }

    private suspend fun getViews(orders: List<Order>) {
        _orderViews.value = orders.map { order ->
            repository.getOrderView(order.salesmanId, order.customerId, order.items, order)
        }


    }

    private fun loadInternalData() {
        viewModelScope.launch {
            repository.getSalesman()
            repository.getItems()
            repository.getCustomers()
        }
    }

}
