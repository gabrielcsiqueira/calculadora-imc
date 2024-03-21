package com.example.calculadora_imc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NecessidadeCaloricaActivity : AppCompatActivity() {

    private lateinit var editTextIdade: EditText
    private lateinit var editTextAltura: EditText
    private lateinit var editTextPeso: EditText
    private lateinit var buttonCalcular: Button
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_necessidade_calorica)

        editTextIdade = findViewById(R.id.edit_text_idade)
        editTextAltura = findViewById(R.id.edit_text_altura)
        editTextPeso = findViewById(R.id.edit_text_peso)
        buttonCalcular = findViewById(R.id.button_calcular_ncd)
        radioGroup = findViewById(R.id.radio_group)

        val textViewResultado = findViewById<TextView>(R.id.text_resultado)

        buttonCalcular.setOnClickListener {
            val peso = editTextPeso.text.toString().toDouble()
            val altura = editTextAltura.text.toString().toDouble()
            val idade = editTextIdade.text.toString().toDouble()

            val valorSelecionado = when (radioGroup.checkedRadioButtonId) {
                R.id.radio_feminino -> "Mulher"
                R.id.radio_masculino -> "Homem"
                else -> ""
            }

            val dnc = calculaDnc(peso, altura, idade, valorSelecionado)
            val dncFormatado = String.format("%.2f", dnc)
            textViewResultado.text = "Resultado: $dncFormatado"
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun validaForm(): Boolean {
        var error = false

        if (editTextIdade.text.isEmpty()) {
            editTextIdade.error = "Digite sua idade"
            error = true
        } else {
            editTextIdade.error = ""
        }

        if (editTextPeso.text.isEmpty()) {
            editTextPeso.error = "Digite seu Peso"
            error = true
        } else {
            editTextPeso.error = ""
        }

        if (editTextAltura.text.isEmpty()) {
            editTextAltura.error = "Digite sua altura"
            error = true
        } else {
            editTextAltura.error = ""
        }

        return error
    }

    private fun calculaDnc(peso: Double, altura: Double, idade: Double, genero: String): Double {
        return when (genero) {
            "Homem" -> 66.47 + (13.75 * peso) + (5.00 * altura) - (6.76 * idade)
            "Mulher" -> 655.1 + (9.56 * peso) + (1.85 * altura) - (4.68 * idade)
            else -> 0.0
        }
    }
}