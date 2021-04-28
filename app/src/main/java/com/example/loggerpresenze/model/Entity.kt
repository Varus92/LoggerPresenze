package com.example.presenze.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "User")
data class EntityUser(
    @PrimaryKey(autoGenerate = true) val idUser: Int = 0,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "nome") val nome: String,
    @ColumnInfo(name = "cognome") val cognome: String,
    @ColumnInfo(name = "Ruolo") val ruolo: String,
    @ColumnInfo(name = "materia") val materia: String,

)

@Entity(
    tableName = "Lezioni"
)
data class EntityLezioni(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "docente") val idDocenteL: Int,
    @ColumnInfo(name = "materia") val idMateria: String,
    @ColumnInfo(name = "studente") val studente: Int,
    @ColumnInfo(name = "presenza") val presenza: String,
    @ColumnInfo(name = "data") val data: String
)

