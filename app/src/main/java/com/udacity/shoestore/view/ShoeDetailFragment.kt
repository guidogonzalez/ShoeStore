package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodel.ShoeViewModel

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        binding.lifecycleOwner = this
        binding.shoeViewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSave.setOnClickListener {

            if (!binding.editTextShoeName.text.isNullOrEmpty() && !binding.editTextShoeSize.text.isNullOrEmpty() &&
                !binding.editTextShoeCompany.text.isNullOrEmpty() && !binding.editTextShoeDescription.text.isNullOrEmpty()
            ) {

                viewModel.addShoe()

                findNavController().navigateUp()

            } else {

                Toast.makeText(context, getString(R.string.error_empty_fields), Toast.LENGTH_LONG)
                    .show()
            }
        }

        binding.buttonCancel.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}