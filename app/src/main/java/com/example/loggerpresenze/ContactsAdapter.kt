package com.example.loggerpresenze

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.presenze.db.DbPresenze
import com.example.presenze.model.EntityUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ContactsAdapter (private val mContacts: List<EntityUser>, val db:DbPresenze) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val cognomeTextView = itemView.findViewById<TextView>(R.id.CognomeListaStudente)
        val nameTextView = itemView.findViewById<TextView>(R.id.ListaNomeStudente)
        val deleteView =itemView.findViewById<ImageButton>(R.id.imageButton2)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.layout_blog_list_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ContactsAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val contact: EntityUser = mContacts.get(position)
        // Set item views based on your views and data model
        val textView = viewHolder.nameTextView
        val textView1 = viewHolder.cognomeTextView
        textView.setText(contact.nome)
        textView1.setText(contact.cognome)

        viewHolder.deleteView.setOnClickListener {
           GlobalScope.launch(Dispatchers.IO) {
               db.userDao().deleteByUserId(contact.idUser)
               
           }
        }


    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mContacts.size
    }
}