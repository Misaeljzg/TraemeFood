package com.misaeljzg.traemefood.sesion

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.misaeljzg.traemefood.R
import com.misaeljzg.traemefood.databinding.FragmentLoginBinding
import com.misaeljzg.traemefood.network.ComprobarRed

class LoginFragment : Fragment() {
    private lateinit var viewModel: SesionVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)


        binding.btnRegistro.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registroFragment))
        Log.i("GameFragment", "Called ViewModelProvider")
        viewModel = ViewModelProvider(this).get(SesionVM::class.java)
        binding.btnLogin.setOnClickListener {
            if(ComprobarRed.hayRed(requireParentFragment().requireContext())) {
                viewModel.login(binding.etLoginCorreo.text.toString(), binding.etLoginPassword.text.toString())
                viewModel.loginStatus.observe(viewLifecycleOwner, Observer {user ->
                    if(user==true){
                        findNavController().navigate(R.id.action_loginFragment_to_restaurantesFragment)
                    }else{
                        Toast.makeText(requireParentFragment().requireContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            else {
                Toast.makeText(requireParentFragment().requireContext(), "No hay red", Toast.LENGTH_SHORT).show()
            }

        }
        return binding.root
    }
}