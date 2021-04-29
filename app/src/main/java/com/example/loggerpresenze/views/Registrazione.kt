package com.example.loggerpresenze.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import com.example.loggerpresenze.R
import com.example.loggerpresenze.databinding.ActivityRegistrazioneBinding
import com.example.loggerpresenze.utilities.transizioneToLogin
import com.example.presenze.db.DbPresenze
import com.example.presenze.model.EntityUser
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Registrazione : AppCompatActivity() {

    fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(this, message, duration).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRegistrazioneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Registrazione"

        GlobalScope.launch(Dispatchers.IO) {
            val db = DbPresenze.getDatabase(this@Registrazione)
            val dbEmail: List<String> = db.userDao().getAllEmail()

            val regista = binding.RegistrazioneCompletata
            val nome = binding.Nome
            val cognome = binding.Cognome
            val email = binding.EmailRegister
            val password = binding.PasswordRegister
            val reppassword = binding.RepeatPassword
            var ruolo = "NonDefinito"
            val ruoloD = binding.radioDocente
            val ruoloS = binding.radioStudente
            ruoloD.setOnClickListener { ruolo = onRadioButtonRuolo(ruoloD) }
            ruoloS.setOnClickListener { ruolo = onRadioButtonRuolo(ruoloS) }
            var materia = "NonDefinito"
            val materiaI = binding.radioIos
            val materiaA = binding.Android
            materiaA.setOnClickListener { materia = onRadioButtonMateria(materiaA) }
            materiaI.setOnClickListener { materia = onRadioButtonMateria(materiaI) }


            regista.setOnClickListener {
                val emailS: String = email.text.toString()
                val pass: String = password.text.toString()
                val reppass: String = reppassword.text.toString()

                if (materia == "NonDefinito") {
                    it.snack("Inserire Materia")
                   // Log.d("G", "errata materia ")

                } else {

                    if (ruolo == "NonDefinito") {
                        it.snack("Inserire Ruolo")
                       // Log.d("G", "errata ruolo")
                    } else {
                        val n: String = nome.text.toString()
                        val c: String = cognome.text.toString()

                        if (validEmailPassword(dbEmail, emailS, pass, reppass, it)) {
                            GlobalScope.launch {
                                //val db = DbPresenze.getDatabase(this@Registrazione)
                                db.userDao().insert(
                                    EntityUser(
                                        nome = n,
                                        cognome = c,
                                        email = emailS,
                                        password = pass,
                                        ruolo = ruolo,
                                        materia = materia
                                    )
                                )

                            }
                            val intent = Intent(baseContext, DashboardStudente::class.java)
                            intent.putExtra("EXTRA_SESSION_ID", emailS)

                            if(ruolo.equals("Studente") ){
                                startActivity(intent)
                            }else{
                                transizioneToLogin(this@Registrazione)
                            }

                        }
                    }
                }
            }

        }


    }


    fun validEmailPassword(
        email: List<String>,
        username: String,
        password: String,
        repPassword: String,
        view: View
    ): Boolean {

        if (username.contains('@') && !email.contains(username)) {
            if (password.length > 5 && repPassword == password) {
                return true
            }
            if(password.length < 5 ) {
                view.snack("Password minore di 5 ")
                return false
            }
            else{
                view.snack("Password diverse")
                return false
            }
        } else {
            view.snack("email errata o e' nel DB")
            return false
        }
    }

    fun onRadioButtonMateria(view: View): String {

        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_ios ->
                    if (checked) {
                        return "IOS"
                    }
                R.id.Android ->
                    if (checked) {
                        return "ANDROID"
                    }
            }
        }
        return "NonDefinito"
    }

    fun onRadioButtonRuolo(view: View): String {

        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_docente ->
                    if (checked) {
                        return "Docente"
                    }
                R.id.radio_studente ->
                    if (checked) {
                        return "Studente"
                    }
            }
        }
        return "NonDefinito"
    }

}