package com.example.loggerpresenze.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loggerpresenze.R
import com.example.presenze.model.EntityLezioni

class StudenteAdapters(private val Lezioni: List<EntityLezioni>) : RecyclerView.Adapter<StudenteAdapters.ViewHolder>(){


    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        val dataView = itemView.findViewById<TextView>(R.id.DataAssenza)
        val materiaView = itemView.findViewById<TextView>(R.id.materiaAssenza)
        val assenzaView = itemView.findViewById<TextView>(R.id.StudenteAssenza)



    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudenteAdapters.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.layout_dashbord_studente,parent,false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: StudenteAdapters.ViewHolder, position: Int) {

        // Get the data model based on position
        val contact: EntityLezioni = Lezioni.get(position)
        // Set item views based on your views and data model
        val textView = viewHolder.dataView
        textView.setText(contact.data)
        val textView1 = viewHolder.materiaView
        textView1.setText(contact.idMateria)
        val textView2 = viewHolder.assenzaView
        textView2.setText(contact.presenza)


    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return Lezioni.size
    }


}