package com.example.dundermifflinapp.ui.create.customer

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.ext.hideKeyboard
import com.example.dundermifflinapp.ext.popBackStack
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateCustomerFragment : Fragment() {

    private val viewModel: CreateCustomerViewModel by viewModel()

    private var root: LinearLayout? = null
    private var toolbar: Toolbar? = null
    private var closeButton: ImageButton? = null
    private var createButton: ImageButton? = null
    private var progressBar: ProgressBar? = null
    private var inputName: EditText? = null
    private var inputAddress: EditText? = null
    private var inputEmail: EditText? = null

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_customer, container, false)

        root = view.findViewById(R.id.root)
        toolbar = view.findViewById(R.id.toolbar)
        progressBar = view.findViewById(R.id.progressBar)
        inputName = view.findViewById(R.id.inputName)
        inputAddress = view.findViewById(R.id.inputAddress)
        inputEmail = view.findViewById(R.id.inputEmail)

        return view
    }

    private fun setupViews() {
        with (activity as? AppCompatActivity) {
            this?.setSupportActionBar(toolbar)
            this?.supportActionBar?.setHomeButtonEnabled(true)
            this?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar?.title = "You are creating a customer"
        }

        toolbar?.setNavigationOnClickListener {
            onCloseButtonClicked()
        }
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

    private fun setupBindings() {
        viewModel.uiState.observe(viewLifecycleOwner) {
            progressBar?.isVisible = it.loading

            if (it.error?.consumed == false) {
                val error = it.error.consume() ?: return@observe
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                popBackStack()
            }

            if (it.succeed?.consumed == true) {
                val success = it.succeed.consume() ?: return@observe
                Toast.makeText(context, success, Toast.LENGTH_LONG).show()
                popBackStack()
            }
        }
    }

    private fun onCreateButtonClicked() {
        view?.hideKeyboard()
        val name = inputName?.text?.trim().toString()
        val address = inputAddress?.text?.trim().toString()
        val email = inputEmail?.text?.trim().toString()

        viewModel.saveCustomer(name, address, email)
    }


    private fun onCloseButtonClicked() {
        findNavController().navigate(R.id.action_createCustomerFragment_to_createMenuFragment)
    }
}