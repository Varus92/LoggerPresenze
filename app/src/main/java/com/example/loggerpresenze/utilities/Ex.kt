package com.example.loggerpresenze.utilities

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.loggerpresenze.views.*

fun Activity.transizioneRegistrati(activity: Context?) {
    val intent = Intent(activity, Registrazione::class.java)
    startActivity(intent)
}

fun Activity.transizioneToLogin(activity: Context?) {
    val intent = Intent(activity,Dashboard::class.java)
    startActivity(intent)
}
fun Activity.transizioneToLoginStudente(activity: Context?) {
    val intent = Intent(activity,DashboardStudente::class.java)
    startActivity(intent)
}

fun Activity.transizioneToProfilo(activity: Context?) {
    val intent = Intent(activity,Profilo::class.java)
    startActivity(intent)
}

fun Activity.transizioneToStudenti(activity: Context?) {
    val intent = Intent(activity,StudentiLista::class.java)
    startActivity(intent)
}

fun Activity.transizioneToHome(activity: Context?) {
    val intent = Intent(activity,MainActivity::class.java)
    startActivity(intent)
}


