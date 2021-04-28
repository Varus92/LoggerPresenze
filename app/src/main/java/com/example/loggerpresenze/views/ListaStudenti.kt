package com.example.loggerpresenze.views

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.loggerpresenze.databinding.ActivityListaStudentiBinding
import com.example.presenze.db.DbPresenze
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ListaStudenti : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListaStudentiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.IO) {
            val db = DbPresenze.getDatabase(this@ListaStudenti)
            val EmailUser = intent.getStringExtra("EXTRA_SESSION_ID")
            val User = db.userDao().elementProfile(EmailUser.toString())
            db.userDao().listaStudenti(User[0].materia)
            binding.ListaStudMateria.setText(User[0].materia)

//            var i = 0
//            var stud: Array<String>
//            while (i< User.size){
//               val nameUser = arrayOf(User[i].nome)
//               val cognomeUser = arrayOf(User[i].cognome)
//                stud= arrayOf(arrayOf(nameUser,cognomeUser).toString())
//                i+=1
//           }



        }



    }
}