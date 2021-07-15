package com.example.dundermifflinapp.ui.create.order.customers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.data.models.Customer
import com.example.dundermifflinapp.data.models.Salesman
import com.example.dundermifflinapp.ext.popBackStack
import com.example.dundermifflinapp.ui.create.order.CreateOrderViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectCustomerFragment : Fragment() {

    private val viewModel: CreateOrderViewModel by viewModel()

    private var toolbar: Toolbar? = null
    private var customersList: RecyclerView? = null

    private val adapter = CustomerAdapter()

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
        val view = inflater.inflate(R.layout.fragment_select_customer, container, false)

        toolbar = view.findViewById(R.id.toolbar)
        customersList = view.findViewById(R.id.customersList)

        return view
    }

    private fun setupViews() {
        with (activity as? AppCompatActivity) {
            this?.setSupportActionBar(toolbar)
            this?.supportActionBar?.setHomeButtonEnabled(true)
            this?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar?.title = "Whos is buying?"
        }

        toolbar?.setNavigationOnClickListener {
            onCloseButtonClicked()
        }

        customersList?.let {
            it.adapter = adapter
        }

        adapter.onItemSelected = {
            onItemSelected(it as Customer)
        }
    }

    private fun setupBindings() {
        viewModel.customers.observe(viewLifecycleOwner) {
            adapter.setCustomers(it)
        }
    }

    private fun onCloseButtonClicked() {
        popBackStack()
    }

    private fun onItemSelected(customer: Customer) {
        viewModel.onCustomerSelected(customer)
        prepareBundle()
    }

    private fun prepareBundle() {
        val salesman = arguments?.getString("salesmanId")
        val bundle = bundleOf(
            "salesmanId" to salesman,
            "customerId" to viewModel.selectedCustomer.value?.customerId
        )
        findNavController().navigate(R.id.action_selectCustomerFragment_to_selectItemsFragment, bundle)
    }
}