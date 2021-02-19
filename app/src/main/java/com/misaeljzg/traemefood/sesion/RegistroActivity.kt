package com.misaeljzg.traemefood.sesion

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.misaeljzg.traemefood.MainActivity
import com.misaeljzg.traemefood.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_registro.*
import java.util.*

class RegistroActivity : AppCompatActivity() {
    private  var miShowPass =  false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        btnReg_send.setOnClickListener {
            val nombre  = etReg_Nombre.text.toString()
            val apellidos  = etReg_Apellidos.toString()
            val direccion  =etReg_Direccion.text.toString()
            val contrasena = et_Reg_Password.text.toString()
            val repetir_contrasena = et_Reg_Password_Conf.text.toString()
            val  numero  = et_Reg_Telefono .text.toString()
            val correo = etReg_Email.text.toString()
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            if (nombre.isEmpty() || direccion.isEmpty() || contrasena.isEmpty() || apellidos.isEmpty() || numero.isEmpty() ||correo.isEmpty()|| repetir_contrasena.isEmpty()){
                Toast.makeText(this,"Verifica Todos Los Campos No Puede Quedar Ningun Campo Vacio",Toast.LENGTH_SHORT).show()
                if (correo.matches(emailPattern.toRegex())){

                }else{

                    Toast.makeText(applicationContext, "Direccion de correo invalida",
                            Toast.LENGTH_SHORT).show()
                }
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo,contrasena)
                    .addOnCompleteListener{
                        if (!it.isSuccessful) return@addOnCompleteListener
                        uploadImageToFirebaseStorage()
                        AlertDialog.Builder(this).apply {
                            setTitle("Cuenta Creada")
                            setMessage("Tu cuenta Ha sido Creada Correctamente, Bienvenidos  a Traeme Food ")
                            setPositiveButton("Aceptar", null)
                        }.show()
                    }
                    .addOnFailureListener{
                        androidx.appcompat.app.AlertDialog.Builder(this).apply {
                            setTitle("Error de inicio")
                            setMessage(it.message)
                            setPositiveButton("Aceptar", null)
                        }.show()
                    }
        }
        Mostrar()

        ib_Selectfoto.setOnClickListener {
            Register_photo()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0  &&  resultCode== Activity.RESULT_OK && data != null ){
        if(selectphoto_mini == null){
            Toast.makeText(this,"ingresa una foto no puede ir vacio",Toast.LENGTH_SHORT).show()
        }else{
            selectedPhotoUri  =data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,selectedPhotoUri)
            selectphoto_mini.setImageBitmap(bitmap)
            ib_Selectfoto.alpha =0f}
        }
    }
    var selectedPhotoUri :  Uri?= null

    private  fun Register_photo(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type ="image/"
        startActivityForResult(intent,0)
    }
    private fun  uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) return
        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images_User$filename")
        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    saveUserToFirebaseDatabase(it.toString())
                }
            }
    }

    private fun saveUserToFirebaseDatabase(profileImageUrl: String){
        val uid=  FirebaseAuth.getInstance().uid?: ""
        val ref= FirebaseDatabase.getInstance().getReference("/user/$uid")
        val user = User (uid, etReg_Nombre.text.toString(), etReg_Apellidos.text.toString(),etReg_Email.text.toString() ,etReg_Direccion.text.toString(), et_Reg_Telefono .text.toString() ,profileImageUrl)
        ref.setValue(user)
            .addOnSuccessListener {
                val intent = Intent(this,LoginActivity::class.java)
                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }.addOnFailureListener{
            }
    }

    private fun showPassword(isShow:Boolean){

        if(isShow) {
            et_Reg_Password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            et_Reg_Password_Conf.transformationMethod = HideReturnsTransformationMethod.getInstance()
            passwordvisi.setImageResource(R.drawable.ic_baseline_visibility_24)
            passwordvisi_conf.setImageResource(R.drawable.ic_baseline_visibility_24)
        }else {
            et_Reg_Password.transformationMethod =PasswordTransformationMethod.getInstance()
            et_Reg_Password_Conf.transformationMethod =PasswordTransformationMethod.getInstance()
            passwordvisi.setImageResource(R.drawable.ic_baseline_visibility_off_24)
            passwordvisi_conf.setImageResource(R.drawable.ic_baseline_visibility_off_24)
        }
        et_Reg_Password.setSelection(et_Reg_Password.text.toString().length)
        et_Reg_Password_Conf.setSelection(et_Reg_Password_Conf.text.toString().length)

    }

    private fun Mostrar(){
        passwordvisi.setOnClickListener {
            miShowPass=!miShowPass
            showPassword(miShowPass)
        }
        showPassword(miShowPass)
        passwordvisi_conf.setOnClickListener {
            miShowPass=!miShowPass
            showPassword(miShowPass)
        }
        showPassword(miShowPass)
    }
}



