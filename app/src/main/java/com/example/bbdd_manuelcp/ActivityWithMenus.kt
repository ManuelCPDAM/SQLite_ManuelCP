package com.example.bbdd_manuelcp

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class ActivityWithMenus : AppCompatActivity() {
    /**
     * Contador para saber en que menú estamos
     */
    companion object{
        var actividadActual=0;
    }

    /**
     * Quita el enabled cuando estemos en el menú correspondiente
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater =menuInflater
        inflater.inflate(R.menu.menu_principal,menu)

        if (menu != null) {
            for(i in 0 until menu.size()){
                if(i== actividadActual) menu.getItem(i).isEnabled = false
                else menu.getItem(i).isEnabled=true
            }
        }

        return true
    }

    /**
     * Selecciona la activity correspondiente al menú seleccionado
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.AnadirAlumno->{
                val intent = Intent(this, Anadir::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                actividadActual=0;
                startActivity(intent)
                true
            }
            R.id.ActualizarAlumno->{
                val intent = Intent(this,Actualizar::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                actividadActual=1;
                startActivity(intent)
                true
            }
            R.id.EliminarAlumno->{
                val intent = Intent(this,Eliminar::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                actividadActual=2;
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}