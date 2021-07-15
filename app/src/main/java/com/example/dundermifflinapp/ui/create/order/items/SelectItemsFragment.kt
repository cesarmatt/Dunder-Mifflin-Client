package com.example.dundermifflinapp.ui.create.order.items

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.data.models.OrderItem
import com.example.dundermifflinapp.ext.popBackStack
import com.example.dundermifflinapp.ui.create.order.CreateOrderViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectItemsFragment : Fragment() {
    // Different menu with create :D
    // Show number of selected items with value at bottom

    private val viewModel: CreateOrderViewModel by viewModel()

    private var root: LinearLayout? = null
    private var toolbar: Toolbar? = null
    private var itemsList: RecyclerView? = null

    private val adapter = ItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupBindings()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select_items, container, false)

        root = view.findViewById(R.id.root)
        toolbar = view.findViewById(R.id.toolbar)
        itemsList = view.findViewById(R.id.itemsList)

        return view
    }

    private fun setupViews() {
        with (activity as? AppCompatActivity) {
            this?.setSupportActionBar(toolbar)
            this?.supportActionBar?.setHomeButtonEnabled(true)
            this?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar?.title = "What is the customer buying?"
        }

        toolbar?.setNavigationOnClickListener {
            onCloseButtonClicked()
        }

        itemsList?.let {
            it.adapter = adapter
        }

        adapter.onItemSelected = {
            onItemSelected(it as OrderItem)
        }
    }

    private fun setupBindings() {
        viewModel.items.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }

        viewModel.uiState.observe(viewLifecycleOwner) {
            if (it.error?.consumed == false) {
                val error = it.error.consume() ?: return@observe
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_selectItemsFragment_to_feedFragment)
            }

            if (it.succeed?.consumed == true) {
                val success = it.succeed.consume() ?: return@observe
                Toast.makeText(context, success, Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_selectItemsFragment_to_feedFragment)
            }
        }
    }

    private fun onCloseButtonClicked() {
        popBackStack()
    }

    private fun onItemSelected(item: OrderItem) {
        viewModel.onItemsSelected(listOf(item))
    }

    private fun onCreateButtonClicked() {
        val salesman = arguments?.getString("salesmanId")
        val customer = arguments?.getString("customerId")
        val items = viewModel.selectedItems.value
        val value = calculateValue()
        viewModel.saveOrder(
            customer!!,
            salesman!!,
            items!!,
            value
        )
    }

    private fun calculateValue(): Float {
        var total: Float = 0F
        viewModel.selectedItems.value?.forEach { item ->
            total += item.value as Float
        }

        return total
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.create) {
            onCreateButtonClicked()
        }
        return super.onOptionsItemSelected(item)
    }
}