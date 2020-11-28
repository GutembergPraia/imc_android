package br.com.hefestos.imc

import android.annotation.SuppressLint
import android.content.Context
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
import br.com.hefestos.imc.model.Imc
import java.math.BigDecimal
import kotlin.math.pow


class CalcImcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcimc)

        val altura = findViewById<EditText>(R.id.edtTNumDecAltura)
        val peso = findViewById<EditText>(R.id.edtTNumDecPeso)
        val imc = findViewById<TextView>(R.id.textViewImc)
        val imcText = findViewById<TextView>(R.id.textViewImcT)
        val button: Button = findViewById(R.id.btCalc)


        // Register the onClick listener with the implementation above
        button.setOnClickListener {
            //Log.d("TAG", peso.text.isEmpty().toString())
            calcular(altura, peso, imc, imcText, it)
        }
    }
    private fun calcular(
            alt: EditText,
            pes: EditText,
            imc: TextView,
            imcText: TextView,
            it: View
    ) {
        val calcularImc = Imc(
                altura = alt?.text.toString().toBigDecimalOrNull() ?: BigDecimal.ZERO,
                peso = pes?.text.toString().toBigDecimalOrNull() ?: BigDecimal.ZERO
        )

        imc.text = calcularImc.valorImc.toString()
        imcText.text = calcularImc.textImc.first
        imc.setTextColor(Color.parseColor(calcularImc.textImc.second))

        //Fecha o teclado
        it.hideKeyboard()
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


}
