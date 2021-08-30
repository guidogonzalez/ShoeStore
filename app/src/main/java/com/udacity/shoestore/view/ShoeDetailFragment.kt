package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ShoeViewModel

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSave.setOnClickListener {

            val shoe = Shoe(
                binding.editTextShoeName.text.toString(),
                binding.editTextShoeSize.text.toString().toDouble(),
                binding.editTextShoeCompany.text.toString(),
                binding.editTextShoeDescription.text.toString(),
                ArrayList()
            )

            viewModel.addShoe(shoe)

            findNavController().navigateUp()
        }

        binding.buttonCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}