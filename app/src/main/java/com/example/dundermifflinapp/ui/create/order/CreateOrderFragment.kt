package com.example.dundermifflinapp.ui.create.order

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.data.models.Customer
import com.example.dundermifflinapp.data.models.OrderItem
import com.example.dundermifflinapp.data.models.Salesman
import com.example.dundermifflinapp.ui.components.appbar.NavigationAppBarWithAction
import com.example.dundermifflinapp.ui.create.components.dropdown.DropDownOption
import com.example.dundermifflinapp.ui.create.order.components.customerdropdown.CustomerDropDownComponent
import com.example.dundermifflinapp.ui.create.order.components.itemselector.ItemCheckboxState
import com.example.dundermifflinapp.ui.create.order.components.itemselector.ItemSelectorComponent
import com.example.dundermifflinapp.ui.create.order.components.ordervaluebanner.OrderValueBannerComponent
import com.example.dundermifflinapp.ui.create.order.components.salesmandropdown.SalesmanDropDownComponent
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateOrderFragment : Fragment() {

    private val viewModel: CreateOrderViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                CreateOrderScreen(
                    createOrderViewModel = viewModel,
                    onCreateButtonClicked = { onCreateButtonClicked() },
                    onNavigationButtonClicked = { onNavigationButtonClicked() }
                )
            }
        }
    }

    private fun onNavigationButtonClicked() {
        findNavController().navigate(R.id.action_createOrderFragment_to_createMenuFragment)
    }

    private fun onCreateButtonClicked() {
        viewModel.saveOrder()
    }
}

@Composable
fun CreateOrderScreen(
    createOrderViewModel: CreateOrderViewModel = viewModel(),
    onCreateButtonClicked: () -> Unit,
    onNavigationButtonClicked: () -> Unit
) {
    val salesmanList = createOrderViewModel.salesman.observeAsState(initial = listOf())
    val salesman = salesmanList.value.map {
        DropDownOption(
            label = it.name ?: "",
            content = it
        )
    }

    val customerList = createOrderViewModel.customers.observeAsState(initial = listOf())
    val customers = customerList.value.map {
        DropDownOption(
            label = it.name ?: "",
            content = it
        )
    }

    val itemsList = createOrderViewModel.items.observeAsState(initial = listOf())
    val items = itemsList.value.map {
        ItemCheckboxState(
            isChecked = false,
            content = it
        )
    }

    val orderValue = createOrderViewModel.formState.totalValue?.observeAsState(initial = 0f)

    CreateOrderContent(
        salesmanDropDownOptions = salesman,
        customerDropDownOptions = customers,
        items = items,
        onCreateButtonClicked = onCreateButtonClicked,
        onNavigationButtonClicked = onNavigationButtonClicked,
        onSalesmanSelected = { createOrderViewModel.onSalesmanSelected(it) },
        onCustomerSelected = { createOrderViewModel.onCustomerSelected(it) },
        onItemSelected = { createOrderViewModel.onItemSelected(it) },
        onItemDeselected = { createOrderViewModel.onItemDeselected(it) },
        orderValue = orderValue?.value ?: 0f
    )
}

@Composable
fun CreateOrderContent(
    salesmanDropDownOptions: List<DropDownOption>,
    customerDropDownOptions: List<DropDownOption>,
    items: List<ItemCheckboxState>,
    onCreateButtonClicked: () -> Unit,
    onNavigationButtonClicked: () -> Unit,
    onSalesmanSelected: (Salesman) -> Unit,
    onCustomerSelected: (Customer) -> Unit,
    onItemSelected: (OrderItem) -> Unit,
    onItemDeselected: (OrderItem) -> Unit,
    orderValue: Float
) {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        topBar = {
            NavigationAppBarWithAction(
                title = "You are creating an order",
                icon = Icons.Filled.Check,
                onActionClick = { onCreateButtonClicked() },
                onNavigationClick = { onNavigationButtonClicked() }
            )
        }
    ) {
        Column {
            Column(modifier = Modifier.padding(8.dp).weight(1f)) {
                SalesmanDropDownComponent(
                    dropDownOptions = salesmanDropDownOptions,
                    onSalesmanSelected = onSalesmanSelected
                )
                CustomerDropDownComponent(
                    dropDownOptions = customerDropDownOptions,
                    onCustomerSelected = onCustomerSelected
                )
                ItemSelectorComponent(
                    items = items,
                    onItemSelected = onItemSelected,
                    onItemDeselected = onItemDeselected
                )
            }
            OrderValueBannerComponent(orderValue = orderValue)
        }
    }
}

@Preview
@Composable
fun CreateOrderContentPreview() {
    CreateOrderContent(
        salesmanDropDownOptions = listOf(),
        customerDropDownOptions = listOf(),
        items = listOf(),
        onCreateButtonClicked = { println("Callback!") },
        onNavigationButtonClicked = { println("Callback!") },
        onSalesmanSelected = { println("Callback!") },
        onCustomerSelected = { println("Callback!") },
        onItemSelected = { println("Callback!") },
        onItemDeselected = { println("Callback!") },
        orderValue = 100f
    )
}