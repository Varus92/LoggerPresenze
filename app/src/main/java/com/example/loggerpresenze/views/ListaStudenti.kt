package com.example.loggerpresenze.views


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loggerpresenze.R
import com.example.loggerpresenze.databinding.ActivityListaStudentiBinding
import com.example.presenze.db.DbPresenze
import kotlinx.android.synthetic.main.activity_lista_studenti.*
import kotlinx.android.synthetic.main.layout_blog_list_item.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ListaStudenti : AppCompatActivity() {

    private lateinit var listStudentAdapter: CustomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityListaStudentiBinding.inflate(layoutInflater)
        setContentView(binding.root)


        GlobalScope.launch(Dispatchers.IO) {
            val db = DbPresenze.getDatabase(this@ListaStudenti)
            val EmailUser = intent.getStringExtra("EXTRA_SESSION_ID")
            val user = db.userDao().elementProfile(EmailUser.toString())

            title = "Lista Studenti di ${user[0].materia}"

            val student = db.userDao().listaStudenti(user[0].materia)

            Log.d("hhhh", student.toString())

            //getting recyclerview from xml
            val recyclerView = findViewById(R.id.recyclerView) as? RecyclerView

            //adding a layoutmanager
            //recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
            //creating our adapter
            val adapter = CustomAdapter(student)

            //now adding the adapter to recyclerview
            if (recyclerView != null) {
                recyclerView.adapter = adapter
            }



            //val items = arrayListOf<String>("cop", "nsnif")
//            val itemsAdapter: ArrayAdapter<String> =
//                ArrayAdapter<String>(this@ListaStudenti, R.layout.simple_list_item_1, items)
//            val listView: ListView = findViewById<View>(R.id.listStud) as ListView
//            listView.setAdapter(itemsAdapter)

        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(com.example.loggerpresenze.R.menu.back, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            com.example.loggerpresenze.R.id.dashboard_option -> {
                startActivity(
                    Intent(
                        this,
                        Dashboard::class.java
                    )
                )
            }
            com.example.loggerpresenze.R.id.logout_option -> {
                startActivity(
                    Intent(
                        this,
                        MainActivity::class.java
                    )
                )
            }

        }

        return super.onOptionsItemSelected(item)
    }

}