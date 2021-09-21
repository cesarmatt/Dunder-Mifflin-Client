package com.example.dundermifflinapp.create.order

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dundermifflinapp.data.create.order.CreateOrderRepository
import com.example.dundermifflinapp.data.models.Customer
import com.example.dundermifflinapp.data.models.OrderItem
import com.example.dundermifflinapp.data.models.Salesman
import com.example.dundermifflinapp.ui.create.order.CreateOrderViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.koin.test.AutoCloseKoinTest
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class CreateOrderViewModelTest : AutoCloseKoinTest() {

    private lateinit var createOrderViewModel: CreateOrderViewModel
    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun before() {
        Dispatchers.setMain(dispatcher)
        createOrderViewModel = CreateOrderViewModel(
            mock(CreateOrderRepository::class.java)
        )
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        stopKoin()
    }

    @Test
    fun `when salesman is selected form state should update selected salesman value`() {
        val selectedSalesman = mock(Salesman::class.java)

        createOrderViewModel.onSalesmanSelected(selectedSalesman)

        val value = createOrderViewModel.formState.selectedSalesman.value
        assertThat(value, equalTo(selectedSalesman))
    }

    @Test
    fun `when customer is selected form state should update customer value`() {
        val selectedCustomer = mock(Customer::class.java)

        createOrderViewModel.onCustomerSelected(selectedCustomer)

        val value = createOrderViewModel.formState.selectedCustomer.value
        assertThat(value, equalTo(selectedCustomer))
    }

    @Test
    fun `when item is selected form state should update order items and value`() {
        val selectedItem = mock(OrderItem::class.java)

        createOrderViewModel.onItemSelected(selectedItem)

        val selectedItemsValue = createOrderViewModel.formState.selectedItems.value
        val orderTotalValue = createOrderViewModel.formState.totalValue.value
        assertThat(selectedItemsValue, equalTo(mutableListOf(selectedItem)))
        assertThat(orderTotalValue, equalTo(0f))
    }

    @Test
    fun `when an item is deselected it should be removed from form state`() {
        val item = mock(OrderItem::class.java)
        createOrderViewModel.formState.selectedItems.value?.add(item)

        createOrderViewModel.onItemDeselected(item)

        val selectedItemsValue = createOrderViewModel.formState.selectedItems.value
        assertThat(selectedItemsValue, equalTo(mutableListOf()))
    }
}