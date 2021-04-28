package com.example.loggerpresenze.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.loggerpresenze.R
import com.example.loggerpresenze.databinding.ActivityDashboardBinding
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

            buttonProfilo.setOnClickListener {
                val intent = Intent(baseContext, Profilo::class.java)
                intent.putExtra("EXTRA_SESSION_ID", EmailUser)
                startActivity(intent)
            }

            buttonStudenti.setOnClickListener {
                val intent1 = Intent(baseContext, ListaStudenti::class.java)
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