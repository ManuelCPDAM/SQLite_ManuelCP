package com.example.bbdd_manuelcp.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room

/**
 * Clase que instancia la database
 */
class MiListaApp: Application() {
    companion object{
        lateinit var database: DBAlumnos
    }

    override fun onCreate() {
        super.onCreate()
        MiListaApp.database = Room.databaseBuilder(this, DBAlumnos::class.java,"DBAlumnos").build()
    }
}

