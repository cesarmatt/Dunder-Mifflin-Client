package com.example.dundermifflinapp.ui.create.order

import androidx.lifecycle.MutableLiveData
import com.example.dundermifflinapp.data.models.Customer
import com.example.dundermifflinapp.data.models.OrderItem
import com.example.dundermifflinapp.data.models.Salesman

class CreateOrderFormState(
    var selectedSalesman: MutableLiveData<Salesman> = MutableLiveData(null),
    var selectedCustomer: MutableLiveData<Customer> = MutableLiveData(null),
    var selectedItems: MutableLiveData<MutableList<OrderItem>> = MutableLiveData(mutableListOf()),
    var totalValue: MutableLiveData<Float> = MutableLiveData(0f)
)