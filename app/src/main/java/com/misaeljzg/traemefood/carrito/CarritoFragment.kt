package com.misaeljzg.traemefood.carrito

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.misaeljzg.traemefood.R
import com.misaeljzg.traemefood.databinding.FragmentCarritoBinding
import com.misaeljzg.traemefood.roomdatabase.OrdenDatabase


class CarritoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCarritoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_carrito, container, false)
        val application = requireNotNull(this.activity).application
        val dataSource = OrdenDatabase.getInstance(application).ordenDAO
        val viewModelFactory = CarritoVMFactory(dataSource, application)
        val carritoVM = ViewModelProvider(this, viewModelFactory).get(CarritoVM::class.java)
        binding.carritoVM = carritoVM
        binding.lifecycleOwner = this
        return binding.root
    }
}