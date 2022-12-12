package com.example.bbdd_manuelcp

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.bbdd_manuelcp.database.Alumnos
import com.example.bbdd_manuelcp.database.DBAlumnos
import com.example.bbdd_manuelcp.database.DBAlumnos_Impl
import com.example.bbdd_manuelcp.database.MiListaApp
import com.example.bbdd_manuelcp.databinding.ActivityAnadirBinding
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Anadir : ActivityWithMenus() {

    /**
     * Habilitamos el binding como siempre
     */
    lateinit var binding: ActivityAnadirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnadirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Añadimos el evento de click al botón de añadir alumno
         * pasandole los parametros de los EditTexts Correspondientes
         */
        binding.BtAnadir.setOnClickListener{

        var nombreAlumno =binding.EtEscribirNombre.text.toString()
        var apellidoAlumno =  binding.EtEscribirApellido.text.toString()
        var cursoAlumno = binding.EtEscribirCurso.text.toString()

        // Comprobamos que no sean nulos ni estén vacios ningun edit text
        if(!nombreAlumno.isNullOrEmpty()&&!apellidoAlumno.isNullOrEmpty()&&!cursoAlumno.isNullOrEmpty()){

                addAlumno(Alumnos(
                    nombre= nombreAlumno,
                    apellido =apellidoAlumno,
                    curso = cursoAlumno)
                )

        }
             else{
            showError()
            }
        }


    }

    /**
     * Error si se dejan los campos vacios
     */
    private fun showError() {
        Toast.makeText(this,"Hay campos vacios, rellenelos todos",Toast.LENGTH_SHORT).show()
    }

    /**
     * Método que añade los alumnos en la base de datos
     */
    fun addAlumno(alumno:Alumnos){
        CoroutineScope(Dispatchers.IO).launch {

            MiListaApp.database.alumnosDAO().addAlumno(alumno)
        }
        runOnUiThread {
            clearItems()
            dismissKeyboard(this)
        }
    }


    /**
     * Limpia los campos una vez se añada a la base de datos
     */
    fun clearItems(){
        binding.EtEscribirCurso.setText("")
        binding.EtEscribirApellido.setText("")
        binding.EtEscribirNombre.setText("")
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