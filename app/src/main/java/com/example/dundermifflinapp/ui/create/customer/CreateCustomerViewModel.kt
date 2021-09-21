package com.example.dundermifflinapp.ui.create.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.data.create.customer.CreateCustomerRepository
import com.example.dundermifflinapp.data.models.Customer
import com.example.dundermifflinapp.utils.Event
import kotlinx.coroutines.launch
import com.example.dundermifflinapp.utils.Result

class CreateCustomerViewModel(private val createCustomerRepository: CreateCustomerRepository) : ViewModel() {

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState>
        get() = _uiState

    val formState = CreateCustomerFormState()

    fun saveCustomer() {
        emitUiState(loading = true)
        val customer = Customer(
            name = formState.name.value,
            address = formState.address.value,
            email = formState.email.value
        )

        println(customer)

        viewModelScope.launch {
            val result = createCustomerRepository.saveCustomer(customer)
            if (result is Result.Success) {
                emitUiState(
                    loading = false,
                    succeed = Event(R.string.msg_created_customer)
                )
            } else {
                emitUiState(
                    loading = false,
                    error = Event(R.string.msg_error_creating_customer)
                )
            }
        }
    }

    fun onNameChanged(name: String) {
        formState.name.value = name
    }

    fun onAddressChanged(address: String) {
        formState.address.value = address
    }

    fun onEmailChanged(email: String) {
        formState.email.value = email
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