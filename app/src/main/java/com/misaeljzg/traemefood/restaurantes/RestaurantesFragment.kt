package com.misaeljzg.traemefood.restaurantes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.misaeljzg.traemefood.R
import com.misaeljzg.traemefood.databinding.FragmentRestaurantesBinding

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
        binding.rvLista.adapter = RestaurantesAC(RestaurantesAC.OnClickListener{
            viewModel.displayRestaurantMenu(it)
        })
        viewModel.navigateToSelectedRestaurant.observe(viewLifecycleOwner, Observer {
            if(null != it){
                this.findNavController().navigate(RestaurantesFragmentDirections.actionRestaurantesFragmentToMenuFragment(it))
                viewModel.displayRestaurantMenuComplete()
            }
        })
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