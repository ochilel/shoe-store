package com.udacity.shoestore.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            signIn.setOnClickListener(::goToWelcome)
            signUp.setOnClickListener(::goToWelcome)
        }
    }

    private fun goToWelcome(view: View) {
        with(binding) {
            val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username.editText?.text.toString())
            view.findNavController().navigate(action)
        }
    }
}