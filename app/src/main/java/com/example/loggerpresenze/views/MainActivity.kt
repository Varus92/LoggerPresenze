package com.example.loggerpresenze.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.loggerpresenze.databinding.ActivityMainBinding
import com.example.loggerpresenze.utilities.transizioneRegistrati
import com.example.loggerpresenze.utilities.transizioneToLogin
import com.example.presenze.db.DbPresenze
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*      GlobalScope.launch(Dispatchers.IO) {
                  val db = DbPresenze.getDatabase(this@MainActivity)
                  db.docenteDao().insert(
                      EntityDocenti(
                          nome = "flavio",
                          cognome = "Spano'",
                          email = "Flavio@gmail.com",
                          password = "ciao123",
                          ruolo = "Studente",
                          materia = "ANDROID"
                      )
                  )


              }
      */

        GlobalScope.launch(Dispatchers.IO) {

            val button = binding.Login
            val email = binding.EmailAddress
            val password = binding.Password

            button.setOnClickListener {

                val input0: String = email.text.toString()
                val input1: String = password.text.toString()
                GlobalScope.launch {
                    val db = DbPresenze.getDatabase(this@MainActivity)

                    when {
                        db.userDao().checkPassword(
                            input0,
                            input1
                        ) ->{
                            if(db.userDao().returnRuolo(input0).equals("Studente") ){
                                val intent = Intent(baseContext, DashboardStudente::class.java)
                                intent.putExtra("EXTRA_SESSION_ID", input0)
                                startActivity(intent)
                            }else{
                                val intent1 = Intent(baseContext, Dashboard::class.java)
                                intent1.putExtra("EXTRA_SESSION_ID", input0)
                                startActivity(intent1)
                               // transizioneToLogin(this@MainActivity)
                        }
                        }
                        !db.userDao().checkEmail(input0) -> it.snack("Email Errata")
                        else -> it.snack("Password Errata")
                    }
                }
            }
        }

        with(binding) {
            //Login.setOnClickListener { transizioneToLogin(this@MainActivity) }
            Registrazione.setOnClickListener { transizioneRegistrati(this@MainActivity) }
        }
    }

    fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(this, message, duration).show()
    }


}