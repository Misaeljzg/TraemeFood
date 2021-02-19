package com.misaeljzg.traemefood.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.misaeljzg.traemefood.R
import com.misaeljzg.traemefood.sesion.User
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_latest_messages.*
import kotlinx.android.synthetic.main.laster_messager_row.view.*

class LatestMessagesActivity : AppCompatActivity() {
    companion object{
        var  currentUser: User? = null
        val  TAG ="LasterMenssages"


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_messages)

        recyclerview_laster_messager.adapter = adapter
        supportActionBar?.title = "Traime Food"
        recyclerview_laster_messager.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))


        // set  item on click listener  on your  adapter

        adapter.setOnItemClickListener { item, view ->
            val   intent  = Intent (this , ChatLogActivity::class.java)
            val  row  = item  as LastetMessageRow
            row .chatPartnerUser
            intent .putExtra(NewMessagerActivity.USER_KEY, row.chatPartnerUser)
            startActivity(intent)

        }


        listenForLatestMessages()
        //setupDummyRows ()
        fetchCurrentUser()

    }
    class LastetMessageRow(val  chatMessage:ChatMessage): Item<ViewHolder>(){
        var chatPartnerUser: User? =  null
        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.message_textView_latest_message.text = chatMessage.text
            val  chatPartnerId : String
            if(chatMessage.fromId == FirebaseAuth.getInstance().uid){
                chatPartnerId = chatMessage.toId
            }else  {
                chatPartnerId = chatMessage.fromId
            }

            val ref = FirebaseDatabase.getInstance().getReference("/users/$chatPartnerId")
            ref. addListenerForSingleValueEvent(object  : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    chatPartnerUser  =  p0.getValue(User::class.java)
                    viewHolder.itemView.username_textView_latest_message.text = chatPartnerUser?.nombre
                    val targetImageView =  viewHolder.itemView.imageView_latest_latest_Message
                    Picasso.get().load(chatPartnerUser?.profileImageUrl).into(targetImageView)
                }
                override fun onCancelled(p0: DatabaseError) {

                }
            })

        }
        override fun getLayout(): Int {
            return R.layout.laster_messager_row
        }
    }
    val latestMessagesmap = HashMap< String,ChatMessage>()
    private fun refreshRecycleViewMessage(){
        adapter.clear()
        latestMessagesmap.values.forEach{
            adapter.add (LastetMessageRow(it))

        }
    }
    private fun  listenForLatestMessages(){
        val fromId = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId")
        ref.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java)  ?: return
                latestMessagesmap[p0.key!!] = chatMessage
                refreshRecycleViewMessage()
                adapter.add(LastetMessageRow(chatMessage))
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?)  {
                val  chatMessage = p0. getValue(ChatMessage::class.java )?:return
                adapter.add(LastetMessageRow(chatMessage))
                latestMessagesmap[p0.key!!] = chatMessage
                refreshRecycleViewMessage()
            }
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }
            override fun onChildRemoved(p0: DataSnapshot) {

            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
    val adapter  = GroupAdapter<ViewHolder>()

    /* private fun  setupDummyRows(){

         adapter .add (LasterMessagerRow())
         adapter .add (LasterMessagerRow())
         adapter .add (LasterMessagerRow())


     }*/
    private fun  fetchCurrentUser(){
        val uid  = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                currentUser =p0.getValue(User::class.java)
                Log.d("latesMessages", "CurrentUser${currentUser?.profileImageUrl}")
            }
            override fun onCancelled(p0: DatabaseError) {


            }
        })
    }


}