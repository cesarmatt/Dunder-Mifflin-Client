package com.example.dundermifflinapp.ui.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.dundermifflinapp.R

class CreateMenuFragment : Fragment() {

    private var toolbar: Toolbar? = null
    private var closeButton: ImageButton? = null
    private var createOrderButton: LinearLayout? = null
    private var registerCustomerButton: LinearLayout? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_menu, container, false)

        toolbar = view?.rootView?.findViewById(R.id.toolbar)
        createOrderButton = view?.findViewById(R.id.createOrderButton)
        registerCustomerButton = view?.findViewById(R.id.registerCustomerButton)

        return view
    }

    private fun setupViews() {
        with (activity as? AppCompatActivity) {
            this?.setSupportActionBar(toolbar)
            this?.supportActionBar?.setHomeButtonEnabled(true)
            this?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar?.title = "Create"
        }

        toolbar?.setNavigationOnClickListener {
            goToFeed()
        }

        createOrderButton?.setOnClickListener {
            goToCreateOrder()
        }

        registerCustomerButton?.setOnClickListener {
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