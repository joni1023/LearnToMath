package com.example.mentalmath

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
        private var level:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nivel.visibility=View.INVISIBLE
        btn_suma.setOnClickListener(this)
        btn_confirm.setOnClickListener(this)
        btn_resta.setOnClickListener(this)
        btn_div.setOnClickListener(this)
        btn_mult.setOnClickListener(this)
        result.hint="presione aqui"
        createSuma()
        result.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btn_confirm.isEnabled = s!!.isNotEmpty()
            }

            override fun afterTextChanged(s: Editable) {
            }

        })



    }

    override fun onClick(v: View) {
        when (v!!.id){
            R.id.btn_resta -> createResta()
            R.id.btn_suma -> createSuma()
            R.id.btn_confirm -> enviarResult()
            R.id.btn_div -> createDivision()
            R.id.btn_mult -> createMultiplicacion()
            else -> Toast.makeText(this, "ninguno", Toast.LENGTH_SHORT).show()
        }


    }

    private fun createSuma(){
        numOne.text= ((0..10).random()).toString()
        numtwo.text= ((0..10).random()).toString()
        signed.text="+"

    }
    private fun createResta(){
        val primer:Int=(0..10).random()
        numOne.text= primer.toString()
        numtwo.text= ((0..primer).random()).toString()
        signed.text="-"

    }

    private fun createMultiplicacion(){
        numOne.text=((0..3).random()).toString()
        numtwo.text=((0..3).random()).toString()
        signed.text="x"

    }

    private  fun createDivision(){
        val divisor:Int=(1..3).random()
        while (true){
            val dividendo:Int=(1..30).random()
            if(dividendo>divisor && dividendo%divisor==0){
                numOne.text=dividendo.toString()
                numtwo.text=divisor.toString()
                signed.text ="/"
                break
            }
        }
    }
    private fun enviarResult() {
        //--lower teclado
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(btn_confirm.windowToken, 0)
        //---
        val num1: Int = Integer.parseInt(numOne.text.toString())
        val num2: Int = Integer.parseInt(numtwo.text.toString())
        val signo = signed.text.toString()
        val res: Int?
        if (result.text.toString().isNotEmpty()){
            res=Integer.parseInt(result.text.toString())
            when (signo) {
                "+" -> if (res == num1 + num2) {
                    correctoylimpiar()
                    progressBar.incrementProgressBy(10)
                    createSuma()
                    } else {
                    incorrectoylimpiar()
                    createSuma()
                    }
                "-" -> if (res == num1 - num2) {
                    correctoylimpiar()
                    progressBar.incrementProgressBy(10)
                    createResta()
                    } else {
                    incorrectoylimpiar()
                    createResta()

                    }
                "/" -> if(res == num1/num2){
                    correctoylimpiar()
                    progressBar.incrementProgressBy(10)
                    createDivision()
                    }else{
                    incorrectoylimpiar()
                    createDivision()
                    }
                "x" -> if(res == num1*num2){
                    correctoylimpiar()
                    progressBar.incrementProgressBy(10)
                    createMultiplicacion()
                }else{
                    incorrectoylimpiar()
                    createMultiplicacion()
                }
            }
    }
        verificarProgreso()
    }

    private fun incorrectoylimpiar(){
        Toast.makeText(this, "incorrecto", Toast.LENGTH_SHORT).show()
        result.text.clear()
    }
    private fun correctoylimpiar(){
        Toast.makeText(this, "correcto", Toast.LENGTH_SHORT).show()
        result.text.clear()
    }

    private fun verificarProgreso(){
        if(progressBar.progress == 100){
            progressBar.progress = 0
            level++
            nivel.text="si lo lograste "+level.toString()+""
            nivel.visibility=View.VISIBLE
        }
    }




}