package com.muhammedhassaan.shoestore.ui.shoelist

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.muhammedhassaan.shoestore.R
import com.muhammedhassaan.shoestore.databinding.FragmentShoeListBinding
import com.muhammedhassaan.shoestore.ui.shoedetails.ShoeViewModel


class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        binding.addFap.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment())
        }


        val menuHost: MenuHost = requireActivity()
        setUpOptionMenu(menuHost)

        viewModel.shoesList.observe(viewLifecycleOwner, Observer {
            for (shoe in viewModel.shoesList.value!!) {
                addView(
                    name = shoe.name,
                    company = shoe.company,
                    size = shoe.size,
                    description = shoe.description
                )
            }
        })

        return binding.root
    }

    private fun navigateToLoginScreen() {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
    }

    private fun setUpOptionMenu(menuHost: MenuHost) {
        menuHost.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.logout_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {
                        R.id.logout -> {
                            logoutDialog()
                            true
                        }
                        else -> false
                    }
                }
            },
            viewLifecycleOwner, Lifecycle.State.RESUMED
        )
    }

    private fun logoutDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("LOGOUT")
            .setMessage("Are you sure you want to log out ?")
            .setIcon(R.drawable.logout)
            .setPositiveButton("Log out", { dialog, which ->
                navigateToLoginScreen()
            }).setNegativeButton("Cancel", { dialog, which ->
                dialog.cancel()
            }).show()
    }

    private fun addView(name: String, company: String, size: Int, description: String) {
        val inflater = LayoutInflater.from(context).inflate(R.layout.shoe_element, null) as View
        var shoeName: TextView = inflater.findViewById(R.id.showName_textview)
        var shoeCompany: TextView = inflater.findViewById(R.id.shoeCompany_textView)
        var shoeSize: TextView = inflater.findViewById(R.id.shoeSize_textView)
        var shoeDescription: TextView = inflater.findViewById(R.id.shoeDescription_textView)
        shoeName.text = name
        shoeCompany.text = company
        shoeSize.text = size.toString()
        shoeDescription.text = description
        binding.shoeListLayout.addView(inflater)
    }

}