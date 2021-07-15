package com.example.dundermifflinapp.ui.create.order

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import com.example.dundermifflinapp.R
import com.example.dundermifflinapp.ext.popBackStack
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateOrderFragment : Fragment() {

    private val viewModel: CreateOrderViewModel by viewModel()

    private var root: LinearLayout? = null
    private var toolbar: Toolbar? = null
    private var progressBar: ProgressBar? = null
    private var salesmanSelector: Spinner? = null
    private var customerSelector: Spinner? = null
    private var itemsSelector: Spinner? = null

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
        val view = inflater.inflate(R.layout.fragment_create_order, container, false)

        root = view.findViewById(R.id.root)
        toolbar = view.findViewById(R.id.toolbar)
        progressBar = view.findViewById(R.id.progressBar)
        salesmanSelector = view.findViewById(R.id.salesmanSelector)
        customerSelector = view.findViewById(R.id.customerSelector)
        itemsSelector = view.findViewById(R.id.itemsSelector)

        return view
    }

    private fun setupViews() {
        with (activity as? AppCompatActivity) {
            this?.setSupportActionBar(toolbar)
            this?.supportActionBar?.setHomeButtonEnabled(true)
            this?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar?.title = "You are registering a order"
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

    private fun onCloseButtonClicked() {
        // TODO
    }

    private fun onCreateButtonClicked() {
        println("Create!")
    }
}