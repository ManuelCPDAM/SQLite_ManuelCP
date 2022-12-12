package com.example.bbdd_manuelcp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Interfaz que contiene las operaciones a realizar en la BD
 */
@Dao
interface AlumnosDAO {

    @Insert
    suspend fun addAlumno(taskEntity: Alumnos):Long

    @Update
    suspend fun updateAlumno(taskEntity: Alumnos):Int

    @Delete
    suspend fun deleteAlumno(taskEntity: Alumnos):Int

    @Query("SELECT * FROM Alumnos WHERE nombre like :nombre")
    suspend fun obtenerAlumnoPorNombre(nombre:String):Alumnos

}