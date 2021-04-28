package com.example.presenze.model

import androidx.room.*


@Dao
interface DaoUser {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg user: EntityUser)

    @Query("UPDATE User SET nome = (:nomeU) , cognome =:cognomeU , password = (:passwordU) WHERE idUser=(:id)")
    fun update(id:Int,nomeU:String,cognomeU:String,passwordU:String)

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
    fun listaStudenti(materia: String, ruolo:String="Studente"): List<EntityUser>


}


@Dao
interface DaoLezioni {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(vararg notes: EntityLezioni)


}


