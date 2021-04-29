package com.example.loggerpresenze.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loggerpresenze.R
import com.example.loggerpresenze.databinding.ActivityPresenzeStudentiBinding
import com.example.loggerpresenze.utilities.transizioneToLogin
import com.example.presenze.db.DbPresenze
import com.example.presenze.model.EntityLezioni
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class PresenzeStudenti : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPresenzeStudentiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title= "Presenze Studenti"
        var conferma = binding.RegisterLesson
        var dashboard = binding.buttonBackDashboard
        val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

        binding.editTextDate.setText(currentDate)
        GlobalScope.launch(Dispatchers.IO) {
            val db = DbPresenze.getDatabase(this@PresenzeStudenti)
            val EmailUser = intent.getStringExtra("EXTRA_SESSION_ID")
            val User = db.userDao().elementProfile(EmailUser.toString())

            val student = db.userDao().listaStudenti(User[0].materia)


            // Lookup the recyclerview in activity layout
            val rvContacts = findViewById<View>(R.id.AssenzaStudentiLista) as RecyclerView

            // Create adapter passing in the sample user data
            val adapter = Adapters(student, db, User[0].idUser)
            // Attach the adapter to the recyclerview to populate items
            // Attach the adapter to the recyclerview to populate items
            rvContacts.adapter = adapter
            // Set layout manager to position the items
            // Set layout manager to position the items
            rvContacts.layoutManager = LinearLayoutManager(this@PresenzeStudenti)
            // That's all!


            dashboard.setOnClickListener {
                val intent = Intent(baseContext, Dashboard::class.java)
                intent.putExtra("EXTRA_SESSION_ID",EmailUser )
                startActivity(intent)            }

            conferma.setOnClickListener {

                GlobalScope.launch(Dispatchers.IO) {

                    if (!db.lezioniDao().registrolezioni(currentDate.toString(), User[0].idUser)) {
                        db.lezioniDao().insertNote(
                            EntityLezioni(
                                idDocenteL = User[0].idUser,
                                idMateria = User[0].materia,
                                data = currentDate.toString(),
                                studente = 0,
                                presenza = "Lezione"
                            )
                        )
                    }
                }
                val intent1 = Intent(baseContext, Dashboard::class.java)
                intent1.putExtra("EXTRA_SESSION_ID",EmailUser )
                startActivity(intent1)
            }


        }

    }
}