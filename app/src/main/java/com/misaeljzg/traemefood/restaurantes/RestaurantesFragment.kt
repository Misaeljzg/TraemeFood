package com.misaeljzg.traemefood.restaurantes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.misaeljzg.traemefood.R
import com.misaeljzg.traemefood.databinding.FragmentLoginBinding
import com.misaeljzg.traemefood.databinding.FragmentRestaurantesBinding
import com.misaeljzg.traemefood.sesion.SesionVM
import kotlinx.coroutines.launch

class RestaurantesFragment : Fragment() {
    private  val viewModel: RestaurantesVM by lazy {
        ViewModelProvider(this).get(RestaurantesVM::class.java)
    }


   // val adaptador: RecyclerView.Adapter = RecyclerView.Adapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = FragmentRestaurantesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.rvLista.adapter = ACRestaurantes()
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                ||super.onOptionsItemSelected(item)
    }

}