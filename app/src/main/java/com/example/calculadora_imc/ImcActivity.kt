package com.example.calculadora_imc

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class ImcActivity : AppCompatActivity() {

    private lateinit var editTextPeso: EditText
    private lateinit var editTextAltura: EditText
    private lateinit var buttonCalcular: Button
    private lateinit var textInputLayoutPeso: TextInputLayout
    private lateinit var textInputLayoutAltura: TextInputLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)

        editTextPeso = findViewById(R.id.edit_text_peso)
        editTextAltura = findViewById(R.id.edit_text_altura)
        buttonCalcular = findViewById(R.id.button_calcular)

        textInputLayoutAltura = findViewById(R.id.text_input_layout_altura)
        textInputLayoutPeso = findViewById(R.id.text_input_layout_peso)

        val spinnerSexo = findViewById<Spinner>(R.id.spinner_sexo)

        val options = arrayOf("Feminino", "Masculino", "Não Informar")

        spinnerSexo.adapter = ArrayAdapter<String>(this, R.layout.spinner_item, options)

        buttonCalcular.setOnClickListener {
            validaForm()
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
}