package com.example.dundermifflinapp.ui.create.order.salesman

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.data.models.Salesman
import com.example.dundermifflinapp.ext.popBackStack
import com.example.dundermifflinapp.ui.create.order.CreateOrderViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectSalesmanFragment : Fragment() {

    val viewModel: CreateOrderViewModel by viewModel()

    private var toolbar: Toolbar? = null
    private var salesmanList: RecyclerView? = null

    private val adapter = SalesmanAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupBindings()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select_salesman, container, false)

        toolbar = view.findViewById(R.id.toolbar)
        salesmanList = view.findViewById(R.id.salesmanList)

        return view
    }

    private fun setupViews() {
        with (activity as? AppCompatActivity) {
            this?.setSupportActionBar(toolbar)
            this?.supportActionBar?.setHomeButtonEnabled(true)
            this?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar?.title = "Who is making the sale?"
        }

        toolbar?.setNavigationOnClickListener {
            onCloseButtonClicked()
        }

        salesmanList?.let {
            it.adapter = adapter
        }

        adapter.onItemSelected = {
            onItemSelected(it as Salesman)
        }
    }

    private fun setupBindings() {
        viewModel.salesman.observe(viewLifecycleOwner) {
            adapter.setSalesman(it)
        }
    }

    private fun onCloseButtonClicked() {
        popBackStack()
    }

    private fun onItemSelected(salesman: Salesman) {
        viewModel.onSalesmanSelected(salesman)
        val bundle = bundleOf("salesmanId" to viewModel.selectedSalesman.value?.id)
        findNavController().navigate(R.id.action_selectSalesmanFragment_to_selectCustomerFragment, bundle)
    }
}