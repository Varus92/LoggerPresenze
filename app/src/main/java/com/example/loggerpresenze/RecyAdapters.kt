package com.example.loggerpresenze

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presenze.model.EntityUser
import kotlinx.android.synthetic.main.layout_blog_list_item.view.*


class RecyAdapters: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: List<EntityUser> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BlogViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_blog_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is BlogViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<EntityUser>){
        items = blogList
    }



    class BlogViewHolder
    constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

       val blog_title = itemView.ListaNomeStudente
        val blog_author = itemView.CognomeListaStudente

        fun bind(blogPost: EntityUser){

            blog_title.setText(blogPost.nome)
            blog_author.setText(blogPost.cognome)

        }

    }

}