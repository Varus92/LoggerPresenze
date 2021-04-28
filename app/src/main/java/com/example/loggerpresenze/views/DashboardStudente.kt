package com.example.loggerpresenze.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.loggerpresenze.R
import com.example.loggerpresenze.databinding.ActivityDashboardStudenteBinding

class DashboardStudente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDashboardStudenteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ButtonProfilo = binding.DashboardProfiloStudente
        val EmailUser = intent.getStringExtra("EXTRA_SESSION_ID")
        Log.d("AAAAAA", EmailUser.toString())

        val intent = Intent(baseContext, Profilo::class.java)
        intent.putExtra("EXTRA_SESSION_ID", EmailUser)

        ButtonProfilo.setOnClickListener {

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