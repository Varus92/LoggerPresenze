package com.example.loggerpresenze.views

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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

class Adapters(private val Student: List<EntityUser>, val db: DbPresenze,val idDocente:Int) : RecyclerView.Adapter<Adapters.ViewHolder>(){


    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        val nameView = itemView.findViewById<TextView>(R.id.ListaNomeStudenteA)
        val cognomeView = itemView.findViewById<TextView>(R.id.CognomeListaStudenteA)
        val buttonView = itemView.findViewById<RadioButton>(R.id.radioButton2)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapters.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.layout_presenze_assenze, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: Adapters.ViewHolder, position: Int) {

        // Get the data model based on position
        val contact: EntityUser = Student.get(position)
        // Set item views based on your views and data model
        val textView = viewHolder.nameView
        textView.setText(contact.nome)
        val textView1 = viewHolder.cognomeView
        textView1.setText(contact.cognome)


        val idUser = Student[position].idUser
        val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())


        var click = 0
        viewHolder.buttonView.setOnClickListener {
            if(click == 0 ) {
                click = 1
                GlobalScope.launch(Dispatchers.IO) {

                    if (!db.lezioniDao().registroStudentelezioni(currentDate.toString(), idUser)) {
                        db.lezioniDao().insertNote(
                            EntityLezioni(
                                idDocenteL = idDocente,
                                idMateria = Student[position].materia,
                                data = currentDate.toString(),
                                studente = idUser,
                                presenza = "Assente"
                            )
                        )
                    }
                }
            }
        }
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return Student.size
    }


}