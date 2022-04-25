package com.udacity.shoestore.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentHomeBinding
import com.udacity.shoestore.models.Shoe
import androidx.fragment.app.activityViewModels

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            lifecycleOwner = this@HomeFragment
            homeViewModel.getShoeLiveData().observe(viewLifecycleOwner, {
                if (it.isNotEmpty()) {
                    createShoes(it)
                }
            })

            fab.setOnClickListener(::goToDetail)

            setHasOptionsMenu(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun createShoes(shoes: List<Shoe>) {
        context?.let { context ->
            val shoeContainer = binding.list
            shoes.forEach { shoe ->
                val shoeLayout = HomeLayout(context)
                shoeLayout.loadShoe(shoe)
                shoeContainer.addView(shoeLayout)
            }
        }
    }

    private fun goToDetail(view: View) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
        view.findNavController().navigate(action)
    }
}