package com.example.dundermifflinapp.ui.feed

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.dundermifflinapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModel()

    private var toolbar: Toolbar? = null
    private var orderList: RecyclerView? = null

    private val adapter = OrderAdapter()

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
        val view = inflater.inflate(R.layout.fragment_feed, container, false)

        toolbar = view?.rootView?.findViewById(R.id.toolbar)
        orderList = view?.findViewById(R.id.orderList)

        return view
    }

    private fun setupViews() {
        with (activity as? AppCompatActivity) {
            this?.setSupportActionBar(toolbar)
            this?.supportActionBar?.setHomeButtonEnabled(false)
            this?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            toolbar?.title = "Dunder Mifflin"
        }

        orderList?.let {
            it.adapter = adapter
        }
    }

    private fun goToCreateMenu() {
        findNavController().navigate(R.id.action_feedFragment_to_createMenuFragment)
    }

    private fun setupBindings() {
        viewModel.orderViews.observe(viewLifecycleOwner) {
            adapter.setOrders(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_feed, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.create) {
            goToCreateMenu()
        }
        return super.onOptionsItemSelected(item)
    }
}