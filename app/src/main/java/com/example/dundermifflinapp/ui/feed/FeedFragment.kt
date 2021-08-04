package com.example.dundermifflinapp.ui.feed

import android.os.Bundle
import android.view.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.data.models.OrderView
import com.example.dundermifflinapp.ui.feed.components.OrderItem
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.dundermifflinapp.ui.components.appbar.AppBar as AppBar

class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FeedScreen(viewModel) {
                    goToCreateMenu()
                }
            }
        }
    }

    private fun goToCreateMenu() {
        findNavController().navigate(R.id.action_feedFragment_to_createMenuFragment)
    }

}

@Composable
fun FeedScreen(
    feedViewModel: FeedViewModel = viewModel(),
    onCreateButtonClicked: () -> Unit
) {
    val orders: List<OrderView> by feedViewModel.orderViews.observeAsState(listOf())
    FeedContent(
        ordersList = orders,
        onCreateButtonClicked = onCreateButtonClicked
    )
}

@Composable
private fun FeedContent(ordersList: List<OrderView>, onCreateButtonClicked: () -> Unit) {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        topBar = {
            AppBar(
                title = "Dunder Mifflin",
                onActionClick = { onCreateButtonClicked() }
            )
        },
    ) {

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(ordersList) {
                OrderItem(order = it)
            }
        }
    }
}

