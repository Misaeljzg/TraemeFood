package com.misaeljzg.traemefood.sesion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.misaeljzg.traemefood.R
import com.misaeljzg.traemefood.databinding.FragmentRegistroBinding


class RegistroFragment : Fragment() {
    private lateinit var viewModel: SesionVM


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding : FragmentRegistroBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_registro, container, false
        )
        Log.i("GameFragment", "Called ViewModelProvider")
        viewModel = ViewModelProvider(this).get(SesionVM::class.java)

        binding.ibSelectfoto.setOnClickListener{
            val  intent  = Intent (Intent.ACTION_PICK)
            intent.type ="image/*"
            startActivityForResult(intent, 0)
        }

        binding.btnRegSend.setOnClickListener {
            val nombre  = binding.etRegNombre.text.toString()
            val direccion  = binding.etRegDireccion.text.toString()
            val contrasena = binding.etRegPassword.text.toString()
            val repetirContrasena = binding.etRegPasswordConf.text.toString()
            val  numero  = binding.etRegTelefono.text.toString()
            val correo = binding.etRegEmail.text.toString()
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            // validar Campos
            if (nombre.isEmpty() || direccion.isEmpty() || contrasena.isEmpty() || repetirContrasena.isEmpty() || numero.isEmpty() ||correo.isEmpty()){
                Toast.makeText(requireParentFragment().requireContext(),"Verifica Todos Los Campos No Puede Quedar Ningun Campo Vacio",Toast.LENGTH_SHORT).show()
            } else{
                if (correo.matches(emailPattern.toRegex())){
                }else{
                    Toast.makeText(requireParentFragment().requireContext(), "Direccion de correo invalida", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }


}