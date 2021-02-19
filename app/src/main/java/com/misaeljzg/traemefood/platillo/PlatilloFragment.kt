//package com.misaeljzg.traemefood.platillo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.misaeljzg.traemefood.carrito.CarritoActivity
import com.misaeljzg.traemefood.chat.NewMessagerActivity
import com.misaeljzg.traemefood.databinding.FragmentPlatilloBinding
import com.misaeljzg.traemefood.platillo.PlatilloVM
import com.misaeljzg.traemefood.platillo.PlatilloVMFactory
import com.misaeljzg.traemefood.roomdatabase.OrdenDatabase


class PlatilloFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val dataSource = OrdenDatabase.getInstance(application).ordenDAO
        val binding = FragmentPlatilloBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val platillo = PlatilloFragmentArgs.fromBundle(requireArguments()).selectedPlatillo
        val viewModelFactory = PlatilloVMFactory(dataSource, platillo , application)
        binding.lifecycleOwner = this
        val viewModel = ViewModelProvider(this, viewModelFactory).get(PlatilloVM::class.java)
        binding.viewModel = viewModel

        binding.btnPlatilloMas.setOnClickListener {
            viewModel.addCantidad(platillo.precioPlatillo.toLong())
        }

        binding.btnPlatilloMenos.setOnClickListener {
            viewModel.menosCantidad(platillo.precioPlatillo.toLong())
        }

        binding.btnIrACarrito.setOnClickListener {
            val intent = Intent(this.context, CarritoActivity::class.java).apply {
                putExtra("ID_Rest", viewModel.selectedPlatillo.value!!.idRestaurante)
            }
            startActivity(intent)
        }
        binding.btnChat.setOnClickListener {
            val intent = Intent(this.context,NewMessagerActivity::class.java)
            startActivity(intent)
        }



        viewModel.cantidadPlatillo.observe(viewLifecycleOwner, Observer {
            binding.tvPlatilloCantidad.text = it.toString()
        })

        viewModel.total.observe(viewLifecycleOwner, Observer {
            binding.tvPlatilloTotal.text = it.toString()
        })
        return binding.root



    }


}