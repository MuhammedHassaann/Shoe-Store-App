package com.muhammedhassaan.shoestore.ui.shoedetails

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.muhammedhassaan.shoestore.R
import com.muhammedhassaan.shoestore.databinding.FragmentShoeDetailsBinding


class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsBinding
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_details,
            container,
            false
        )
        binding.shoeViewModel = viewModel
        binding.lifecycleOwner = this

        binding.cancelButton.setOnClickListener {
            navigateToShoeListScreen()
        }

        binding.saveButton.setOnClickListener {
            viewModel.addShoe()
            navigateToShoeListScreen()
        }

        return binding.root
    }


    private fun navigateToShoeListScreen() {
        findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment())
    }

}