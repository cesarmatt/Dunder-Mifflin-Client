package com.example.dundermifflinapp.ui.create.customer

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.findNavController
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.ui.components.appbar.NavigationAppBarWithAction
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dundermifflinapp.utils.Event

class CreateCustomerFragment : Fragment() {

    private val viewModel: CreateCustomerViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                CreateCustomerScreen(
                    onCreateButtonClicked = { onCreateButtonClicked() },
                    onNavigationButtonClicked = { onNavigationButtonClicked() },
                    viewModel = viewModel
                )
            }
        }
    }

    private fun onCreateButtonClicked() {
        viewModel.saveCustomer()
    }


    private fun onNavigationButtonClicked() {
        findNavController().navigate(R.id.action_createCustomerFragment_to_createMenuFragment)
    }
}

@Composable
fun CreateCustomerScreen(
    viewModel: CreateCustomerViewModel = viewModel(),
    onCreateButtonClicked: () -> Unit,
    onNavigationButtonClicked: () -> Unit
) {
    val name = rememberSaveable { mutableStateOf("") }
    val address = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val uiState = viewModel.uiState.observeAsState()

    CreateCustomerContent(
        onCreateButtonClicked = { onCreateButtonClicked() },
        onNavigationButtonClicked = { onNavigationButtonClicked() },
        nameState = name,
        addressState = address,
        emailState = email,
        onNameChanged = { viewModel.onNameChanged(it) },
        onAddressChanged = { viewModel.onAddressChanged(it) },
        onEmailChanged = { viewModel.onEmailChanged(it) },
        uiState = uiState
    )
}

@Composable
fun CreateCustomerContent(
    onCreateButtonClicked: () -> Unit,
    onNavigationButtonClicked: () -> Unit,
    nameState: MutableState<String>,
    addressState: MutableState<String>,
    emailState: MutableState<String>,
    onNameChanged: (String) -> Unit,
    onAddressChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    uiState: State<UIState?>
) {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        topBar = {
            NavigationAppBarWithAction(
                title = "You are registering a new customer",
                icon = Icons.Filled.Check,
                onActionClick = { onCreateButtonClicked() },
                onNavigationClick = { onNavigationButtonClicked() }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Name") },
                value = nameState.value,
                onValueChange = {
                    nameState.value = it
                    onNameChanged(it)
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Address") },
                value = addressState.value,
                onValueChange = {
                    addressState.value = it
                    onAddressChanged(it)
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Email") },
                value = emailState.value,
                onValueChange = {
                    emailState.value = it
                    onEmailChanged(it)
                }
            )
        }
        if (uiState.value?.succeed?.consumed == true) {
            Text(
                "Sexo",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview
fun CreateCustomerPreview() {
    val name = rememberSaveable { mutableStateOf("") }
    val address = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val uiState = rememberSaveable {
        mutableStateOf(
            UIState(
                loading = false,
                error = Event(R.string.msg_error_creating_customer),
                succeed = Event(R.string.msg_error_creating_customer)
            )
        )
    }

    CreateCustomerContent(
        onCreateButtonClicked = { println("Callback!") },
        onNavigationButtonClicked = { println("Callback!") },
        nameState = name,
        addressState = address,
        emailState = email,
        onNameChanged = { println("Callback!") },
        onAddressChanged = { println("Callback!") },
        onEmailChanged = { println("Callback!") },
        uiState = uiState
    )
}