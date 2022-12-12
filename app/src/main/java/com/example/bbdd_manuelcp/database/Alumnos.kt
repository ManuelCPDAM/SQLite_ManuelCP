package com.example.bbdd_manuelcp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Tabla alumnos
 */
@Entity(tableName = "Alumnos")
data class Alumnos (
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
    @ColumnInfo var nombre: String="",
    @ColumnInfo var apellido: String="",
    @ColumnInfo var curso: String=""
)