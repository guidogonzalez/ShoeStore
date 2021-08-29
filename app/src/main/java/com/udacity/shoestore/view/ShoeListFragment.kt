package com.udacity.shoestore.view

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.viewmodel.ShoeViewModel

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private lateinit var viewModel: ShoeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        // We observe the list of shoes and go through the loop to add a view for each shoe
        viewModel.shoeLists.observe(viewLifecycleOwner, Observer { newShoeList ->

            newShoeList.forEach { shoe ->

                val itemShoeBinding: ItemShoeBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.item_shoe, container, false)
                itemShoeBinding.shoe = shoe
                binding.linearLayoutShoeList.addView(itemShoeBinding.root)
            }
        })

        binding.floatingActionButtonAdd.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        when (item!!.itemId) {
            R.id.menu_logout -> {
                viewModel.clearShoes()
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
            }
        }
        return super.onOptionsItemSelected(item)
    }
}