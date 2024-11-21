package com.example.pmdm_ae1_android_calculadorasalarioneto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val salary = findViewById<EditText>(R.id.salary)
        val paymentsNum = findViewById<EditText>(R.id.payment_num)
        val age = findViewById<EditText>(R.id.age)
        val profGroup = findViewById<Spinner>(R.id.profesional_group)
        val civilState = findViewById<Spinner>(R.id.civil_state)
        val hasDisability = findViewById<CheckBox>(R.id.has_disability)
        val childrenNum = findViewById<EditText>(R.id.children_num)
        val btnCalculate = findViewById<Button>(R.id.btn_calculate)

        ArrayAdapter.createFromResource(
            this,
            R.array.grupos_profesionales,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            profGroup.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.estados_civiles,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            civilState.adapter = adapter
        }

        btnCalculate.setOnClickListener {
            val userSalary = salary.text.toString().toDoubleOrNull() ?: 0.0
            val userPayments = paymentsNum.text.toString().toIntOrNull() ?: 1
            val userAge = age.text.toString().toIntOrNull() ?: 0
            val userProfGroup = profGroup.selectedItemPosition + 1
            val userState = civilState.selectedItemPosition + 1
            val userHasDisability = hasDisability.isChecked
            val userChildren = childrenNum.text.toString().toIntOrNull() ?: 0

            val salarioNeto = calcularSalarioNeto(
                userSalary,
                userPayments,
                userAge,
                userProfGroup,
                userState,
                userHasDisability,
                userChildren
            )

            Toast.makeText(this, "El salario neto es: $salarioNeto", Toast.LENGTH_LONG).show()

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("SALARY_RESULT", salarioNeto)
            startActivity(intent)
        }
    }

    private fun calcularSalarioNeto(
        userSalary: Double,
        userPayments: Int,
        userAge: Int,
        userProfGroup: Int,
        userState: Int,
        userHasDisability: Boolean,
        userChildren: Int
    ): Double {

        val retencionIRPF = when (userProfGroup) {
            1 -> userSalary * 0.22
            2 -> userSalary * 0.20
            3 -> userSalary * 0.18
            4 -> userSalary * 0.16
            5 -> userSalary * 0.14
            6 -> userSalary * 0.12
            7 -> userSalary * 0.10
            8 -> userSalary * 0.08
            else -> userSalary * 0.15
        }

        val deduccionuserState = when (userState) {
            2 -> 1500.0
            4 -> 1200.0
            6 -> 1000.0
            else -> 0.0
        }

        val deducciones = (if (userHasDisability) 1000.0 else 0.0) + userChildren * 500 + deduccionuserState

        return userSalary - retencionIRPF + deducciones
    }
}
