package com.example.dundermifflinapp.ui.create.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.data.create.order.CreateOrderRepository
import com.example.dundermifflinapp.data.models.Customer
import com.example.dundermifflinapp.data.models.Order
import com.example.dundermifflinapp.data.models.OrderItem
import com.example.dundermifflinapp.data.models.Salesman
import com.example.dundermifflinapp.utils.Event
import com.example.dundermifflinapp.utils.Result
import kotlinx.coroutines.launch

class CreateOrderViewModel(private val createOrderRepository: CreateOrderRepository) : ViewModel() {

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState>
        get() = _uiState

    private val _customers = MutableLiveData<List<Customer>>()
    val customers: LiveData<List<Customer>>
        get() = _customers

    private val _salesman = MutableLiveData<List<Salesman>>()
    val salesman: LiveData<List<Salesman>> = _salesman

    private val _items = MutableLiveData<List<OrderItem>>()
    val items: LiveData<List<OrderItem>>
        get() = _items

    val selectedSalesman = MutableLiveData<Salesman>()
    val selectedCustomer = MutableLiveData<Customer>()
    val selectedItems = MutableLiveData<MutableList<OrderItem>>()

    init {
        loadInternalData()
    }

    fun saveOrder(customerId: String, salesmanId: String, items: List<OrderItem>, value: Float) {
        emitUiState(loading = true)

        val itemsIds: List<String> = items.map { it.orderItemId }

        val order = Order(
            customerId = customerId,
            salesmanId = salesmanId,
            items = itemsIds,
            value = value
        )

        viewModelScope.launch {
            val result = createOrderRepository.saveOrder(order)
            if (result is Result.Success) {
                emitUiState(loading = false, succeed = Event(R.string.msg_created_order))
            } else {
                emitUiState(loading = false, error = Event(R.string.msg_error_creating_order))
            }
        }
    }

    fun onSalesmanSelected(salesman: Salesman) {
        selectedSalesman.value = salesman
    }

    fun onCustomerSelected(customer: Customer) {
        selectedCustomer.value = customer
    }

    fun onItemSelected(selectedItem: OrderItem) {
        selectedItems.value?.add(selectedItem)
    }

    private fun loadInternalData() {
        viewModelScope.launch {
            loadCustomers()
            loadSalesman()
            loadItems()
        }
    }

    private suspend fun loadCustomers() {
        val response = createOrderRepository.getAllCustomers()

        if (response is Result.Success) {
            _customers.value = response.data
        } else {
            emitUiState(loading = false, error = Event(R.string.msg_error_getting_customers))
        }
    }

    private suspend fun loadSalesman() {
        val response = createOrderRepository.getAllSalesman()

        if (response is Result.Success) {
            _salesman.value = response.data
        } else {
            emitUiState(loading = false, error = Event(R.string.msg_error_getting_salesman))
        }
    }

    private suspend fun loadItems() {
        val response = createOrderRepository.getAllItems()

        if (response is Result.Success) {
            _items.value = response.data
        } else {
            emitUiState(loading = false, error = Event(R.string.msg_error_getting_items))
        }
    }

    private fun emitUiState(
        loading: Boolean = false,
        error: Event<Int>? = null,
        succeed: Event<Int>? = null,
    ) {
        _uiState.value = UIState(loading, error, succeed)
    }
}

class UIState(
    val loading: Boolean,
    val error: Event<Int>?,
    val succeed: Event<Int>?
)