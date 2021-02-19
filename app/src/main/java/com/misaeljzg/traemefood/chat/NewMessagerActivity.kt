package com.misaeljzg.traemefood.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY
import android.util.Log
import android.widget.VideoView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.misaeljzg.traemefood.R
import com.misaeljzg.traemefood.sesion.User
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_new_messager.*
import kotlinx.android.synthetic.main.user_row_new_message.view.*

class NewMessagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_messager)

        supportActionBar?.title ="Seleciona un usuario"

        fetchUsers()


    }
    companion   object{
        val USER_KEY ="USER_KEY"
    }


    private fun fetchUsers(){
        val  ref = FirebaseDatabase.getInstance().getReference("/Restaurantes")
        ref.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                p0.children.forEach{

                    val user = it.getValue(User::class.java)
                    if(user != null){
                        adapter.add(UserItem(user))
                    }
                }
                adapter.setOnItemClickListener{ item, view ->

                    val  userItem = item as UserItem
                    val  intent = Intent (view.context, ChatLogActivity::class.java)
                    //  intent.putExtra(USER_KEY,userItem.user.username)
                    intent.putExtra(USER_KEY, userItem.user )
                    startActivity(intent)
                    finish()
                }



                recycleview_newmessage.adapter = adapter
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}
class  UserItem(val user: User) : Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.username_textview_new_message.text = user.nombre

        Picasso.get().load(user.profileImageUrl).into(viewHolder.itemView.imageview_new_message)

    }
    override fun getLayout():Int {
        return R.layout.user_row_new_message

    }
}



