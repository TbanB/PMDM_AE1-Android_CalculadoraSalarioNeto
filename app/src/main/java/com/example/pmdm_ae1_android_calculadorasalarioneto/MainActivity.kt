package com.example.pmdm_ae1_android_calculadorasalarioneto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import com.example.pmdm_ae1_android_calculadorasalarioneto.R.layout.activity_main

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        btn_calcular.setOnClickListener {
            // Recoger los datos del usuario
            val salarioBruto = et_salario_bruto.text.toString().toDoubleOrNull() ?: 0.0
            val numeroPagas = et_numero_pagas.text.toString().toIntOrNull() ?: 1
            val edad = et_edad.text.toString().toIntOrNull() ?: 0
            val grupoProfesional = spinner_grupo_profesional.selectedItem.toString()
            val estadoCivil = spinner_estado_civil.selectedItem.toString()
            val tieneDiscapacidad = checkbox_discapacidad.isChecked
            val numeroHijos = et_numero_hijos.text.toString().toIntOrNull() ?: 0

            // Crear Intent para pasar los datos a la segunda vista
            val intent = Intent(this, ResultadosActivity::class.java).apply {
                putExtra("SALARIO_BRUTO", salarioBruto)
                putExtra("NUMERO_PAGAS", numeroPagas)
                putExtra("EDAD", edad)
                putExtra("GRUPO_PROFESIONAL", grupoProfesional)
                putExtra("ESTADO_CIVIL", estadoCivil)
                putExtra("DISCAPACIDAD", tieneDiscapacidad)
                putExtra("NUMERO_HIJOS", numeroHijos)
            }
            startActivity(intent)
        }
    }
}