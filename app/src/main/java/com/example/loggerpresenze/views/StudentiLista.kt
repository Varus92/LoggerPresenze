package com.example.loggerpresenze.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loggerpresenze.ContactsAdapter
import com.example.loggerpresenze.R
import com.example.loggerpresenze.databinding.ActivityStudentiListaBinding
import com.example.presenze.db.DbPresenze
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StudentiLista : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityStudentiListaBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_studenti_lista)
        setContentView(binding.root)


        GlobalScope.launch(Dispatchers.IO) {
            val db = DbPresenze.getDatabase(this@StudentiLista)
            val EmailUser = intent.getStringExtra("EXTRA_SESSION_ID")
            val user = db.userDao().elementProfile(EmailUser.toString())

            title = "Lista Studenti di ${user[0].materia}"

            val student = db.userDao().listaStudenti(user[0].materia)

            Log.d("hhhh", student.toString())

            // Lookup the recyclerview in activity layout
            val rvContacts = findViewById<View>(R.id.rvContacts) as RecyclerView

            // Create adapter passing in the sample user data
            val adapter = ContactsAdapter(student, db)
            // Attach the adapter to the recyclerview to populate items
            // Attach the adapter to the recyclerview to populate items
            rvContacts.adapter = adapter
            // Set layout manager to position the items
            // Set layout manager to position the items
            rvContacts.layoutManager = LinearLayoutManager(this@StudentiLista)
            // That's all!

        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(com.example.loggerpresenze.R.menu.back, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            com.example.loggerpresenze.R.id.dashboard_option -> {
                startActivity(
                    Intent(
                        this,
                        Dashboard::class.java
                    )
                )
            }
            com.example.loggerpresenze.R.id.logout_option -> {
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    )
                )
            }

        }

        return super.onOptionsItemSelected(item)
    }



}