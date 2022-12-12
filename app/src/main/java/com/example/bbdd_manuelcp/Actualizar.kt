package com.example.bbdd_manuelcp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.bbdd_manuelcp.database.Alumnos
import com.example.bbdd_manuelcp.database.MiListaApp
import com.example.bbdd_manuelcp.databinding.ActivityActualizarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Actualizar : ActivityWithMenus() {
    /**
     * habilitamos binding como siempre
     */
    lateinit var binding: ActivityActualizarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Evento de pulsar el botón actualizar
         */
        binding.BtActualizar.setOnClickListener{

            ActualizarCurso()
        }

    }

    /**
     * Método que actuliza el curso segun el nombre del alumno
     */
    private fun ActualizarCurso() {
        // Guardamos las variables y comprobamos que no estén vacias
        var NombreAlumno = binding.EtNombreEditar.text.toString()
        var NuevoCurso = binding.EtNuevoCurso.text.toString()

        if (!NombreAlumno.isNullOrEmpty()&&!NuevoCurso.isNullOrEmpty()) {

            // declaramos variable de tipo alumnos
            var alumnoActualizar: Alumnos

            CoroutineScope(Dispatchers.IO).launch {
                // Y buscamos el alumno por el nombre
                alumnoActualizar = MiListaApp.database.alumnosDAO().obtenerAlumnoPorNombre(NombreAlumno)

                // Si obtenemos alumno
                if (alumnoActualizar != null) {

                    // asignamos el nuevo valor
                    alumnoActualizar.curso = NuevoCurso

                    // Hacemos el update
                    MiListaApp.database.alumnosDAO().updateAlumno(alumnoActualizar)
                } else {
                    // Error si se dejan campos vacios
                    showError("Alumno no encontrado")
                }

            }
            runOnUiThread {
                clearItems()
                dismissKeyboard(this)
            }
        }
        else{
            showError("Campos vacios, rellenelos todos")
        }
    }

    /**
     * Toast con mensaje de error
     */
    private fun showError(mensaje:String) {
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()
    }
    /**
     * Limpia los campos una vez se añada a la base de datos
     */
    fun clearItems(){
        binding.EtNombreEditar.setText("")
        binding.EtNuevoCurso.setText("")
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


}