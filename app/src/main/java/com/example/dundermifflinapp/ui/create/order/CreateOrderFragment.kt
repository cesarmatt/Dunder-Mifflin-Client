package com.example.dundermifflinapp.ui.create.order

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.isVisible
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.ext.popBackStack
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateOrderFragment : Fragment() {

    private val viewModel: CreateOrderViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                CreateOrderScreen(viewModel)
            }
        }
    }

    private fun onCloseButtonClicked() {
        // TODO
    }

    private fun onCreateButtonClicked() {
        println("Create!")
    }
}

@Composable
fun CreateOrderScreen(createOrderViewModel: CreateOrderViewModel = viewModel()) {
    CreateOrderContent()
}

@Composable
fun CreateOrderContent() {
    Scaffold {
        Text("Salve")
    }
}