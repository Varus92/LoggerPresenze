package com.example.loggerpresenze.views

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import com.example.loggerpresenze.databinding.ActivityMainBinding
import com.example.loggerpresenze.databinding.ActivityPresenzeStudentiBinding
import com.example.loggerpresenze.utilities.transizioneToLogin
import com.example.presenze.db.DbPresenze
import com.example.presenze.model.EntityLezioni
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class PresenzeStudenti : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPresenzeStudentiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title= "Presenze Studenti"
        var conferma = binding.RegisterLesson
        var dashboard = binding.buttonBackDashboard

        GlobalScope.launch(Dispatchers.IO) {
            val db = DbPresenze.getDatabase(this@PresenzeStudenti)
            val EmailUser = intent.getStringExtra("EXTRA_SESSION_ID")
            val User = db.userDao().elementProfile(EmailUser.toString())
            val data = binding.editTextDate
            val idStudente = 1 //cambiare
            val presenzaStudente = "Presente"

            if (false) {
                db.lezioniDao().insertNote(
                    EntityLezioni(
                        idDocenteL = User[0].idUser,
                        idMateria = User[0].materia,
                        data = data.toString(),
                        studente = idStudente,
                        presenza = presenzaStudente
                    )
                )
            }


            dashboard.setOnClickListener {
                transizioneToLogin(this@PresenzeStudenti)
            }

            conferma.setOnClickListener {

            }


        }

    }
}