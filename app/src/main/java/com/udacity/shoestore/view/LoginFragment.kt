package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNewLogin.setOnClickListener {
            if (!checkLoginError()) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            } else {
                showToast()
            }
        }

        binding.buttonLoginExisting.setOnClickListener {
            if (!checkLoginError()) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            } else {
                showToast()
            }
        }
    }

    private fun checkLoginError(): Boolean {

        if (binding.editTextEmail.text.toString()
                .isEmpty() || binding.editTextPassword.text.toString().isEmpty()
        ) {
            return true
        }

        return false
    }

    private fun showToast() {
        Toast.makeText(context, getString(R.string.error_login), Toast.LENGTH_LONG).show()
    }
}