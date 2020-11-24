package br.com.hefestos.imc.model

import java.math.BigDecimal
import java.math.RoundingMode

class Imc (
    val altura: BigDecimal,
    val peso: BigDecimal
    ) {

        val valorImc: BigDecimal
        get() {
            if (this.altura> BigDecimal.ZERO && this.peso> BigDecimal.ZERO){
                return this.peso.divide(this.altura.pow(2),2, RoundingMode.UP)
            }
            return BigDecimal.ZERO
        }

        val textImc: Pair<String,String>
        get() {
            return  when {
                this.valorImc == BigDecimal.ZERO -> Pair("Valor invalido","#ff0000")
                this.valorImc > BigDecimal.ZERO && this.valorImc<= BigDecimal(17) -> Pair("Muito abaixo do peso.","#00ff00")
                this.valorImc > BigDecimal(17) && this.valorImc< BigDecimal(18.5) -> Pair("Abaixo do peso.","#ffff00")
                this.valorImc >= BigDecimal(18.5) && this.valorImc< BigDecimal(25) -> Pair("Peso normal.","#ffff00")
                this.valorImc >= BigDecimal(25) && this.valorImc< BigDecimal(30) ->  Pair("Acima do peso.","#ffff00")
                this.valorImc >= BigDecimal(30) && this.valorImc< BigDecimal(35) -> Pair("Obesidade I.","#ffff00")
                this.valorImc >= BigDecimal(35) && this.valorImc< BigDecimal(40) -> Pair("Obesidade II(severa).","#ff3c00")
                else -> Pair("Obesidade III(mórbida).","#ff0000")
            }

        }
}