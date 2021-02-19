package com.misaeljzg.traemefood.sesion

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
class  User (val uid : String, val nombre : String ,val apellido:String, val correo : String , val direccion : String ,val telefono : String ,val profileImageUrl:String  ):Parcelable{
    constructor():this("","","","","","","")
    // Class User
}