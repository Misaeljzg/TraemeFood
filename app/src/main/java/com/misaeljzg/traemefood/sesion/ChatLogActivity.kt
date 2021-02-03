package com.misaeljzg.traemefood.sesion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import com.example.chat.LatestMessagesActivity.Companion.currentUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.misaeljzg.traemefood.R
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat_log.*
//import kotlinx.android.synthetic.main.chat_from_row.view.*
//import kotlinx.android.synthetic.main.chat_to_row.view.*
class ChatLogActivity : AppCompatActivity() {

    companion object {
        val TAG = "ChatLog"
    }

    val adapter = GroupAdapter<ViewHolder>()
    var toUser :  User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_chat_log)

        recycleview_chat_log.adapter = adapter
      //  toUser  = intent.getParcelableExtra<User>(NewMessagerActivity.USER_KEY)
        supportActionBar?.title = toUser?.nombre

        //setupDummyData()
        //listenForMessage()

        send_button_chat_log.setOnClickListener {
            Log.d(TAG, "Attempt to  send message....")
          //  performSendMessage()
        }
    }

   // private fun listenForMessage() {
        //val  fromId =FirebaseAuth.getInstance().uid
        //val toId = toUser?.uid
      //  val ref = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId")
       /*ref.addChildEventListener(object : ChildEventListener {

          /* override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val chatMessage = p0.getValue(ChatMessage::class.java)
                if (chatMessage != null) {
                    Log.d(TAG, chatMessage.text)
                    if (chatMessage.fromId == FirebaseAuth.getInstance().uid) {
                        val  currentUser = LatestMessagesActivity.currentUser?: return
                        adapter.add(ChatFromItem(chatMessage.text,currentUser))
                    } else {
                        adapter.add(ChatToItem(chatMessage.text,toUser!!))
                    }
                }

            }*/

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {


            }

            override fun onChildRemoved(p1: DataSnapshot) {

            }


        })*/
    }

  //  private fun performSendMessage() {
       // val text = edittext_chat_log.text.toString()
        //val fromId = FirebaseAuth.getInstance().uid
       // val  user = intent.getParcelableExtra<User>(NewMessagerActivity.USER_KEY)
       // val toId = user.uid
       // if (fromId == null) return
        //val reference = FirebaseDatabase.getInstance().getReference("/messages").push()
      //  val reference = FirebaseDatabase.getInstance().getReference("/user-messages/$fromId/$toId").push()

        //val toReference = FirebaseDatabase.getInstance().getReference("/user-messages/$toId/$fromId").push()
      //  val chatMessage = ChatMessage(reference.key!!, text, fromId!!, toId, System.currentTimeMillis() / 1000)
      //  reference.setValue(chatMessage)
          //      .addOnSuccessListener {
          //          Log.d(TAG, "Saved our chat message:${reference.key} ")
            //        edittext_chat_log.text.clear()
              //      recycleview_chat_log.scrollToPosition(adapter.itemCount-1)
        //   }
        //toReference.setValue(chatMessage)
        //val  lastestMessageRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$fromId/$toId")
        //lastestMessageRef.setValue(chatMessage   )
        //val  lastestMessagetoRef = FirebaseDatabase.getInstance().getReference("/latest-messages/$toId/$fromId")
        //lastestMessagetoRef.setValue(chatMessage  )

  //  }
//}
 /*class  ChatFromItem (val text :String,val user :User):Item<ViewHolder>(){

      override fun bind(viewHolder: ViewHolder, position: Int){
          viewHolder.itemView.textView_from_row.text= text
          val  uri  =user.profileImageUrl
          val targetImageView = viewHolder.itemView.Imageview_chat_from_row
          Picasso.get().load(uri).into(targetImageView)
      } override fun getLayout(): Int {
          return R.layout.chat_from_row
          }

    }

    class ChatToItem(val text :String, val user :User):Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.textView_to_row.text = text
        //Load our image user into  star
        val  uri  =user.profileImageUrl
        val targetImageView = viewHolder.itemView.imageview_chat_to_row
        Picasso.get().load(uri).into(targetImageView)

        } override fun getLayout(): Int {
            return R.layout.chat_to_row
            }
    }
*/