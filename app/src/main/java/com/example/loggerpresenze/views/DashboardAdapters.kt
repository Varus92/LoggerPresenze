package com.example.loggerpresenze.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loggerpresenze.R
import com.example.presenze.db.DbPresenze
import com.example.presenze.model.EntityLezioni
import com.example.presenze.model.EntityUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DashboardAdapters(private val Lezioni: List<EntityLezioni>) : RecyclerView.Adapter<DashboardAdapters.ViewHolder>(){


    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        val nameView = itemView.findViewById<TextView>(R.id.ListaLezionoA)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardAdapters.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.layout_lezioni,parent,false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: DashboardAdapters.ViewHolder, position: Int) {

        // Get the data model based on position
        val contact: EntityLezioni = Lezioni.get(position)
        // Set item views based on your views and data model
        val textView = viewHolder.nameView
        textView.setText(contact.data)
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return Lezioni.size
    }


}