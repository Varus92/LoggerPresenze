package com.example.presenze.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DaoUser {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg user: EntityUser)

    @Query("UPDATE User SET nome = (:nomeU) , cognome =:cognomeU , password = (:passwordU) WHERE idUser=(:id)")
    fun update(id: Int, nomeU: String, cognomeU: String, passwordU: String)

    @Query("SELECT email FROM user")
    suspend fun getAllEmail(): List<String>

    @Query("SELECT * FROM user WHERE email IN (:email)")
    fun checkEmail(email: String): Boolean

    @Query("SELECT * FROM user WHERE email IN (:email) AND password IN(:password)")
    fun checkPassword(email: String, password: String): Boolean

    @Query("SELECT Ruolo FROM user WHERE email IN (:email)")
    fun returnRuolo(email: String): String

    @Query("SELECT * FROM user WHERE email IN (:email) LIMIT 1")
    fun elementProfile(email: String): List<EntityUser>

    @Query("SELECT * FROM user WHERE materia IN (:materia) AND ruolo IN(:ruolo) ")
    fun listaStudenti(materia: String, ruolo: String = "Studente"): List<EntityUser>

    @Query("DELETE FROM user WHERE idUser IN(:userId)")
    fun deleteByUserId(userId: Int)

    @Query("DELETE FROM user WHERE  email IN(:userId)")
    fun deleteByEmailUser(userId: String)
}


@Dao
interface DaoLezioni {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(vararg lez: EntityLezioni)

    @Query("DELETE FROM lezioni WHERE id IN(:userId)")
    fun deleteByUserId(userId: Int)

    @Query("SELECT * FROM lezioni WHERE materia IN (:materia) and studente = 0 and presenza=(:presenza) GROUP BY data")
    fun listaLezioni(materia:String, presenza:String = "Lezione"): List<EntityLezioni>

    @Query("SELECT * FROM lezioni WHERE materia IN (:materia) and studente IN (:idstudente) and presenza=(:presenza) GROUP BY data")
    fun listaAssenzeLezioni(materia:String,idstudente:Int, presenza:String = "Assente"): List<EntityLezioni>

    @Query(" SELECT COUNT(studente) FROM lezioni WHERE studente IN(:idStudente) GROUP BY data ")
     fun numeroAssenze(idStudente: Int): Int

    @Query(" SELECT COUNT(studente) FROM lezioni WHERE materia IN(:materia) and studente = 0 GROUP BY data  ")
    fun numeroLezioni(materia: String ): Int

    @Query("SELECT * FROM lezioni WHERE data = (:data) AND docente IN (:docente) LIMIT 1")
    fun registrolezioni(data:String, docente:Int): Boolean

    @Query("SELECT * FROM lezioni WHERE data = (:data) AND studente IN (:studente) LIMIT 1")
    fun registroStudentelezioni(data:String, studente:Int): Boolean
}


