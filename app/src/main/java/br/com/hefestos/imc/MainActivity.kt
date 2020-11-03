package br.com.hefestos.imc

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val altura = findViewById<EditText>(R.id.edtTNumDecAltura)
        val peso = findViewById<EditText>(R.id.edtTNumDecPeso)
        val imc = findViewById<TextView>(R.id.textViewImc)
        val imcText = findViewById<TextView>(R.id.textViewImcT)
        val button: Button = findViewById(R.id.btCalc)


        // Register the onClick listener with the implementation above
        button.setOnClickListener {
            //Log.d("TAG", peso.text.isEmpty().toString())
            imc.text = calcImc(altura,peso)
            imc.setTextColor(Color.parseColor(colorImc(imc.text.toString())))
            imcText.text = textImc(imc.text.toString())
        }
    }

    fun calcImc(altura: EditText, peso: EditText):String {

        val alt =  altura?.text.toString().toFloatOrNull() ?:0f
        val pes = peso?.text.toString().toFloatOrNull() ?:0f

        if ((alt > 0) and (pes> 0))
            return "%.1f".format(pes / alt.pow(2))

        return "valor invalido"

    }

    fun colorImc(imc: String):String {

        val valor = imc?.replace(",", ".").toFloatOrNull() ?:0f

        when{
            valor == 0f -> {
                return "#ff0000"
            }
            valor < 18.5 -> {
                return "#00ff00"
            }
            valor in 18.5..24.9 -> {
                return "#ffff00"
            }
            valor in 25.0..29.9 -> {
                return "#ffff00"
            }
            valor in 30.0..34.9 -> {
                return "#ff3c00"
            }
            valor in 35.0..39.9 -> {
                return "#ff1e00"
            }
            valor > 40.0 -> {
                return "#ff0000"
            }
            else ->{
                return "#ff0000"
            }
        }
    }

    fun textImc(imc: String):String {

        val valor = imc?.replace(",", ".").toFloatOrNull() ?:0f

        when{
            valor == 0f -> {
                return ""
            }
            valor < 18.5 -> {
                return "Peso Baixo"
            }
            valor in 18.5..24.9 -> {
                return "Peso Normal"
            }
            valor in 25.0..29.9 -> {
                return "Sobre Peso"
            }
            valor in 30.0..34.9 -> {
                return "Obesidade I"
            }
            valor in 35.0..39.9 -> {
                return "Obesidade II"
            }
            valor > 40.0 -> {
                return "Obesidade III"
            }
            else ->{
                return ""
            }
        }
    }
}
