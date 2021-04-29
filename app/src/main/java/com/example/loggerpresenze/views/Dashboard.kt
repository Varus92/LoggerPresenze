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
import com.example.loggerpresenze.ContactsAdapter
import com.example.loggerpresenze.R
import com.example.loggerpresenze.databinding.ActivityDashboardBinding
import com.example.presenze.db.DbPresenze
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Dashboard"
        val EmailUser = intent.getStringExtra("EXTRA_SESSION_ID")


        val buttonProfilo = binding.ProfiloButton
        val buttonStudenti = binding.StudnetiButton
        val buttonAdd =binding.AddLezione
        GlobalScope.launch(Dispatchers.IO) {
            val db = DbPresenze.getDatabase(this@Dashboard)
            val User = db.userDao().elementProfile(EmailUser.toString())

            val lezioni = db.lezioniDao().listaLezioni(User[0].materia)
            Log.d("cnsnik",lezioni.toString())


            // Lookup the recyclerview in activity layout
            val rvContacts = findViewById<View>(R.id.ListaLezioniDocenteDashboard) as RecyclerView

            // Create adapter passing in the sample user data
            val adapter = DashboardAdapters(lezioni)
            // Attach the adapter to the recyclerview to populate items
            // Attach the adapter to the recyclerview to populate items
            rvContacts.adapter = adapter
            // Set layout manager to position the items
            // Set layout manager to position the items
            rvContacts.layoutManager = LinearLayoutManager(this@Dashboard)
            // That's all!





            buttonProfilo.setOnClickListener {
                val intent = Intent(baseContext, Profilo::class.java)
                intent.putExtra("EXTRA_SESSION_ID", EmailUser)
                startActivity(intent)
            }

            buttonStudenti.setOnClickListener {
                val intent1 = Intent(baseContext, StudentiLista::class.java)
                intent1.putExtra("EXTRA_SESSION_ID", EmailUser)
                startActivity(intent1)
            }

            buttonAdd.setOnClickListener {
                val intent2 = Intent(baseContext, PresenzeStudenti::class.java)
                intent2.putExtra("EXTRA_SESSION_ID", EmailUser)
                startActivity(intent2)

            }

        }


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.logout, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.logout_option -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }
}