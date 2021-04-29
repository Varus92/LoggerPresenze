package com.example.loggerpresenze.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loggerpresenze.R
import com.example.loggerpresenze.databinding.ActivityDashboardStudenteBinding
import com.example.presenze.db.DbPresenze
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashboardStudente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDashboardStudenteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title= "Dashboard Studente"

        val ButtonProfilo = binding.DashboardProfiloStudente
        val EmailUser = intent.getStringExtra("EXTRA_SESSION_ID")

        GlobalScope.launch(Dispatchers.IO) {
            val db = DbPresenze.getDatabase(this@DashboardStudente)

            val User = db.userDao().elementProfile(EmailUser.toString())
            val lezioni = db.lezioniDao().listaAssenzeLezioni(User[0].materia,User[0].idUser)

            val assenze = db.lezioniDao().numeroAssenze(User[0].idUser)
            val numerolezioni = db.lezioniDao().numeroLezioni(User[0].materia)
            binding.textView6.setText(assenze.toString())
            val lezioniTot = numerolezioni - assenze
            binding.textView8.setText(lezioniTot.toString())

            // Lookup the recyclerview in activity layout
            val rvContacts = findViewById<View>(R.id.ListaAssenzeStudente) as RecyclerView

            // Create adapter passing in the sample user data
            val adapter = StudenteAdapters(lezioni)
            // Attach the adapter to the recyclerview to populate items
            rvContacts.adapter = adapter
            // Set layout manager to position the items
            rvContacts.layoutManager = LinearLayoutManager(this@DashboardStudente)
            // That's all!

        }

        ButtonProfilo.setOnClickListener {
            val intent = Intent(baseContext, Profilo::class.java)
            intent.putExtra("EXTRA_SESSION_ID", EmailUser)
            startActivity(intent)
        }


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.logout,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.logout_option -> { startActivity(Intent(this,MainActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }
}