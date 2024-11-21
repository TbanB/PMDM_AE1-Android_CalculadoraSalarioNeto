package com.example.pmdm_ae1_android_calculadorasalarioneto

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvResult = findViewById<TextView>(R.id.tv_result)
        val btn = findViewById<Button>(R.id.btn_return);

        val salarioNeto = intent.getDoubleExtra("SALARIO_NETO", 0.0)

        tvResult.text = "El salario neto anual es: €$salarioNeto"
    }
}