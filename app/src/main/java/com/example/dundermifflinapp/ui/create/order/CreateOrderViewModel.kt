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
    val uiState: LiveData<UIState> = _uiState

    private val _formState: CreateOrderFormState = CreateOrderFormState()
//    val formState: LiveData<CreateOrderFormState> = _formState

    private val _customers = MutableLiveData<List<Customer>>()
    val customers: LiveData<List<Customer>> = _customers

    private val _salesman = MutableLiveData<List<Salesman>>()
    val salesman: LiveData<List<Salesman>> = _salesman

    private val _items = MutableLiveData<List<OrderItem>>()
    val items: LiveData<List<OrderItem>> = _items

    init {
        loadInternalData()
    }

    fun saveOrder() {
        emitUiState(loading = true)

        val itemsIds: List<String> = getItemsId()

        val order = Order(
            customerId = _formState.selectedCustomer?.customerId,
            salesmanId = _formState.selectedSalesman?.id,
            items = itemsIds,
            value = _formState.totalValue
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
        _formState.selectedSalesman = salesman
    }

    fun onCustomerSelected(customer: Customer) {
        _formState.selectedCustomer = customer
    }

    fun onItemSelected(selectedItem: OrderItem) {
        _formState.selectedItems.add(selectedItem)
    }

    fun onItemDeselected(selectedItem: OrderItem) {
        _formState.selectedItems.remove(selectedItem)
        updateOrderValue(_formState.selectedItems)
    }

    private fun updateOrderValue(selectedItems: MutableList<OrderItem>?) {
        var total = 0f
        selectedItems?.forEach { item ->
            total += item.value ?: 0f
        }
        _formState.totalValue = total
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

    private fun getItemsId(): List<String> {
        return _formState.selectedItems.map { it.orderItemId } ?: listOf()
    }
}

class UIState(
    val loading: Boolean,
    val error: Event<Int>?,
    val succeed: Event<Int>?
)

class CreateOrderFormState(
    var selectedSalesman: Salesman? = null,
    var selectedCustomer: Customer? = null,
    var selectedItems: MutableList<OrderItem> = mutableListOf(),
    var totalValue: Float = 0f
)