package com.misaeljzg.traemefood.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.misaeljzg.traemefood.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentMenuBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val restaurante = MenuFragmentArgs.fromBundle(requireArguments()).selectedRestaurant
        val viewModelFactory = MenuVMFactory(restaurante, application)
        binding.lifecycleOwner = this
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MenuVM::class.java)
        binding.viewModel = viewModel
        binding.menuLista.adapter = MenuAC(MenuAC.OnClickListener{
            viewModel.displayPlatillo(it)
        })
        viewModel.navigateToSelectedDish.observe(viewLifecycleOwner, Observer {
            if(null!= it){
                this.findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToPlatilloFragment(it))
                viewModel.displayPlatilloComplete()
            }
        })
        return binding.root
    }
}