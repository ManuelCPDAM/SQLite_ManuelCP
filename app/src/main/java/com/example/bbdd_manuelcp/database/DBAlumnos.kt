package com.example.bbdd_manuelcp.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Clase database
 */
@Database(entities = [Alumnos::class],version = 1)

abstract class DBAlumnos:RoomDatabase() {
    abstract fun alumnosDAO(): AlumnosDAO
}


