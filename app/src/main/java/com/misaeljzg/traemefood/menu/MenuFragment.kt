package com.misaeljzg.traemefood.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.misaeljzg.traemefood.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentMenuBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val restaurante = MenuFragmentArgs.fromBundle(
            requireArguments()
        ).selectedRestaurant
        val viewModelFactory =
            MenuVMFactory(
                restaurante,
                application
            )
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(MenuVM::class.java)
        return binding.root
    }
}