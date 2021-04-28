package com.example.loggerpresenze.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.loggerpresenze.databinding.ActivityProfiloBinding
import com.example.loggerpresenze.utilities.transizioneRegistrati
import com.example.loggerpresenze.utilities.transizioneToLogin
import com.example.loggerpresenze.utilities.transizioneToLoginStudente
import com.example.presenze.db.DbPresenze
import com.example.presenze.model.EntityUser
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Profilo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProfiloBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = DbPresenze.getDatabase(this@Profilo)

        val dashboard = binding.Backdashboard
        val modifica = binding.Modificabutton


        GlobalScope.launch(Dispatchers.IO) {

            val EmailUser = intent.getStringExtra("EXTRA_SESSION_ID")
            val User = db.userDao().elementProfile(EmailUser.toString())

            binding.PersonName.setText(User[0].nome)
            binding.CognomeProfiloEdit.setText(User[0].cognome)
            binding.EmailProfile.setText(User[0].email)
            var passwordDB = User[0].password
            var id = User[0].idUser

            dashboard.setOnClickListener {
                if (User[0].ruolo.equals("Studente")) {
                    transizioneToLoginStudente(this@Profilo)
                } else {
                    transizioneToLogin(this@Profilo)  // profile docenti
                }
            }
            modifica.setOnClickListener {
                val nome = binding.PersonName
                val cognome = binding.CognomeProfiloEdit
                val password = binding.ChangePasswordText
                val reppassword = binding.ChangePasswordRepeatText
                Log.d("cncnfffffffnc", passwordDB)

                val n: String = nome.text.toString()
                val c: String = cognome.text.toString()
                val p: String = password.text.toString()
                Log.d("ci", n)
                Log.d("ci", c)
                Log.d("ci", p)
                if (validPassword(passwordDB, password.toString(), reppassword.toString(), it)) {
                    GlobalScope.launch {
                        Log.d("ci", "update")
                        db.userDao().update(
                            id = id,
                            nomeU = n,
                            cognomeU = c,
                            passwordU = p,
                        )
                    }
                }
            }
        }
    }

    fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(this, message, duration).show()
    }

    fun validPassword(
        passwordDb: String,
        password: String,
        repPassword: String,
        view: View
    ): Boolean {

        if (password.length > 5 && repPassword == password && password != passwordDb) {
            view.snack("Password Corta o Uguale")
            return false
        }
        return true
    }


}