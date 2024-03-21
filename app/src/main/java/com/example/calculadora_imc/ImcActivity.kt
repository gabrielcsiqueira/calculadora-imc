package com.example.calculadora_imc

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout
import kotlin.math.pow

class ImcActivity : AppCompatActivity() {

    private lateinit var editTextPeso: EditText
    private lateinit var editTextAltura: EditText
    private lateinit var buttonCalcular: Button
    private lateinit var textInputLayoutPeso: TextInputLayout
    private lateinit var textInputLayoutAltura: TextInputLayout
    private lateinit var spinnerSexo: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)

        editTextPeso = findViewById(R.id.edit_text_peso)
        editTextAltura = findViewById(R.id.edit_text_altura)
        buttonCalcular = findViewById(R.id.button_calcular)

        textInputLayoutAltura = findViewById(R.id.text_input_layout_altura)
        textInputLayoutPeso = findViewById(R.id.text_input_layout_peso)

        spinnerSexo = findViewById<Spinner>(R.id.spinner_sexo)

        val options = arrayOf("Feminino", "Masculino")

        spinnerSexo.adapter = ArrayAdapter<String>(this, R.layout.spinner_item, options)

        val textViewResultado = findViewById<TextView>(R.id.text_resultado)

        buttonCalcular.setOnClickListener {
            if (!validaForm()) {
                textViewResultado.setText(calculaImc())
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun validaForm(): Boolean {
        var error = false

        if (editTextAltura.text.isEmpty()) {
            textInputLayoutAltura.error = "Digite sua altura"
            error = true
        } else {
            textInputLayoutAltura.error = ""
        }

        if (editTextPeso.text.isEmpty()) {
            textInputLayoutPeso.error = "Digite seu Peso"
            error = true
        } else {
            textInputLayoutAltura.error = ""
        }

        /*if (editTextAltura.text.isEmpty()) {
            editTextAltura.error = "Digite sua altura"
        }

        if (editTextPeso.text.isEmpty()) {
            editTextPeso.error = "Digite seu peso"
        }*/

        return error
    }

    fun calculaImc(): String {
        val peso = editTextPeso.text.toString().toDouble()
        val altura = editTextAltura.text.toString().toDouble()
        val sexoPosicao = spinnerSexo.selectedItemPosition

        val sexo = when(sexoPosicao) {
            0 -> "Feminino"
            1 -> "Masculino"
            else -> "Não Informar"
        }

        var imc = peso / (altura.pow(2))

        val imcFormatado = String.format("%.2f", imc)

        if (sexo.equals("Feminino")) {
            imc = imc * 0.9
        }


        val resultado = when {
            imc < 18.5 -> "$imcFormatado = Abaixo do peso"
            imc < 25 -> "$imcFormatado = Peso normal"
            imc < 30 -> "$imcFormatado = Sobrepeso"
            imc < 35 -> "$imcFormatado = Obesidade Grau 1"
            imc < 40 -> "$imcFormatado = Obesidade Grau 2"
            else -> "$imcFormatado = Obesidade Grau 3"
        }

        return resultado
    }
}