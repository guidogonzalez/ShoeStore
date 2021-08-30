package com.udacity.shoestore.view

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.viewmodel.ShoeViewModel
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        // We observe the list of shoes and go through the loop to add a view for each shoe
        viewModel.shoeList.observe(viewLifecycleOwner, Observer { observerShoeList ->

            for (shoe in observerShoeList) {
                val itemShoeBinding: ItemShoeBinding = DataBindingUtil.inflate(inflater, R.layout.item_shoe, container, false)
                itemShoeBinding.shoe = shoe
                binding.linearLayoutShoeList.addView(itemShoeBinding.root)
            }
        })

        binding.floatingActionButtonAdd.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_logout -> {
                viewModel.clearShoes()
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}