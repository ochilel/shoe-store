package com.udacity.shoestore.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.home.HomeViewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            lifecycleOwner = this@DetailFragment
            listViewModel = homeViewModel
            shoe = Shoe()

            save.setOnClickListener(::createShoe)
            cancel.setOnClickListener(::goBack)
        }
    }

    private fun createShoe(view: View) {
        homeViewModel.addShoe(homeViewModel.shoe)
        goBack(view)
    }

    private fun goBack(view: View) {
        val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment()
        view.findNavController().navigate(action)
    }
}