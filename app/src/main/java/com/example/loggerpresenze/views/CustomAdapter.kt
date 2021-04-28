package com.example.loggerpresenze.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loggerpresenze.R
import com.example.presenze.model.EntityUser


class CustomAdapter(val userList: List<EntityUser>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_blog_list_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: EntityUser) {
            val textViewName = itemView.findViewById(R.id.ListaNomeStudente) as TextView
            val textViewAddress  = itemView.findViewById(R.id.CognomeListaStudente) as TextView
            textViewName.text = user.nome
            textViewAddress.text = user.cognome
        }
    }
}