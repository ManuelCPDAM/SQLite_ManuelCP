package com.example.bbdd_manuelcp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.bbdd_manuelcp.database.Alumnos
import com.example.bbdd_manuelcp.database.MiListaApp
import com.example.bbdd_manuelcp.databinding.ActivityEliminarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Eliminar : ActivityWithMenus() {
    /**
     * Habilitamos binding
     */
    lateinit var binding: ActivityEliminarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEliminarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Evento clickar el boton eliminar
         */
        binding.BtEliminar.setOnClickListener{
            EliminarAlumno()
        }
    }

    /**
     * Método que elimina al alumno
     */
    private fun EliminarAlumno() {
        val NombreAlumno = binding.EtNombreEliminar.text.toString()
        // comprobamos que no este vacio
        if (!NombreAlumno.isNullOrEmpty()) {
            // Declaramos la variable de tipo alumno
            var alumnoEliminar: Alumnos

            CoroutineScope(Dispatchers.IO).launch {
                // Buscamos al alumno por el nombre
                alumnoEliminar = MiListaApp.database.alumnosDAO().obtenerAlumnoPorNombre(NombreAlumno)

                // si el alumno existe lo eliminamos
                if(alumnoEliminar!=null){
                    MiListaApp.database.alumnosDAO().deleteAlumno(alumnoEliminar)
                }
                else{
                    showError("Alumno no encontrado")

                }

            }
            runOnUiThread {
                clearItems()
                dismissKeyboard(this)
            }
        }
        else{
            showError("Campo vacio")
        }
    }
    /**
     * Toast con mensaje de error
     */
    private fun showError(mensaje:String) {
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show()
    }
    /**
     * Metodo que oculta el teclado cuando hacemos click en el botón
     */
    fun dismissKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (null != activity.currentFocus) imm.hideSoftInputFromWindow(
            activity.currentFocus!!.applicationWindowToken, 0
        )
    }
    /**
     * Limpia los campos una vez se añada a la base de datos
     */
    fun clearItems(){
        binding.EtNombreEliminar.setText("")
    }
}