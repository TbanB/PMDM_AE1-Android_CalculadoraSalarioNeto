package com.example.pmdm_ae1_android_calculadorasalarioneto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result = findViewById<TextView>(R.id.result)
        val btn = findViewById<Button>(R.id.btn_return);

        val salarioNeto = intent.getDoubleExtra("SALARY_RESULT", 0.0)

        result.text = "El salario neto anual es: €$salarioNeto"

        btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}