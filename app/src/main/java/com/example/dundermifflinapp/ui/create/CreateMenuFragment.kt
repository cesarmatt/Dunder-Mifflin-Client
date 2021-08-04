package com.example.dundermifflinapp.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.fragment.findNavController
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.ui.components.appbar.NavigationAppBar
import com.example.dundermifflinapp.ui.components.appbar.NavigationAppBarWithAction
import com.example.dundermifflinapp.ui.create.MenuAction.Companion.ACTION_CREATE_CUSTOMER
import com.example.dundermifflinapp.ui.create.MenuAction.Companion.ACTION_CREATE_ORDER

class CreateMenuFragment : Fragment() {

    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                CreateMenuContent(
                    actions = getMenuActions()
                ) {
                    goToFeed()
                }
            }
        }
    }

    private fun getMenuActions(): List<MenuAction> {
        return listOf(
            createOrderAction(),
            createCustomerAction()
        )
    }

    private fun createOrderAction(): MenuAction {
        return MenuAction(ACTION_CREATE_ORDER) {
            goToCreateOrder()
        }
    }

    private fun createCustomerAction(): MenuAction {
        return MenuAction(ACTION_CREATE_CUSTOMER) {
            goToRegisterCustomer()
        }
    }

    private fun goToFeed() {
        findNavController().navigate(R.id.action_createMenuFragment_to_feedFragment)
    }

    private fun goToCreateOrder() {
        findNavController().navigate(R.id.action_createMenuFragment_to_selectSalesmanFragment)
    }

    private fun goToRegisterCustomer() {
        findNavController().navigate(R.id.action_createMenuFragment_to_createCustomerFragment)
    }
}

@ExperimentalMaterialApi
@Composable
fun CreateMenuContent(
    actions: List<MenuAction>,
    backButtonAction: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        topBar = {
            NavigationAppBar(
                title = "Create",
                onNavigationClick = { backButtonAction() }
            )
        }
    ) {
        Column {
            actions.forEach { action ->
                CreateMenuItem(menuAction = action)
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun CreateMenuPreview() {
    val sampleAction = MenuAction("Action") { println("ActionClicked") }
    CreateMenuContent(actions = listOf(sampleAction)) {
        println("Back button clicked")
    }
}