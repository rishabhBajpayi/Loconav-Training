package com.example.recyclerview

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var contacts: ArrayList<Contact>
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        val rvContacts = findViewById<View>(R.id.rvContacts) as RecyclerView
        contacts = Contact.createContactsList(20)
        val adapter = ContactsAdapter(contacts)
        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(this)
        super.onCreate(savedInstanceState)

        val btnView : Button = findViewById(R.id.addMore)
        btnView.setOnClickListener {
            contacts.addAll(Contact.createContactsList(5))
            adapter.notifyDataSetChanged()
            println("5 contacts added total : ${adapter.itemCount}")
        }
    }
}

class Contact(private val mName: String, private val mOnline: Boolean){
    val isOnline: Boolean = mOnline
    val name: String = mName

    companion object{
        private var lastContactId = 0
        fun createContactsList(numContacts: Int): ArrayList<Contact> {
            val contacts = ArrayList<Contact>()
            for (i in 1..numContacts) {
                contacts.add(Contact("Person ${++lastContactId}", i % 2 == 0 ))
            }
            return contacts
        }
    }
}