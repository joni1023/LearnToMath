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
        private var operation:String ="+"
        private lateinit var a:String
        private lateinit var b:String

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
        when (v.id){
            R.id.btn_resta -> createResta()
            R.id.btn_suma -> createSuma()
            R.id.btn_confirm -> enviarResult()
            R.id.btn_div -> createDivision()
            R.id.btn_mult -> createMultiplicacion()
            else -> Toast.makeText(this, "ninguno", Toast.LENGTH_SHORT).show()
        }


    }

    private fun createSuma(){
        a= ((0..10).random()).toString()
        b= ((0..10).random()).toString()
        operation="+"
        cuenta.text=a+" "+operation+" "+b

    }
    private fun createResta(){
         a= (0..10).random().toString()
         b= ((0..a.toInt()).random()).toString()
        operation="-"
        cuenta.text= a+" "+operation+" "+b

    }

    private fun createMultiplicacion(){
        a= ((0..10).random()).toString()
        b= ((0..10).random()).toString()
        operation="x"
        cuenta.text=a+" "+operation+" "+b
    }

    private  fun createDivision(){
        val divisor:Int=(1..3).random()
        while (true){
            val dividendo:Int=(1..30).random()
            if(dividendo>divisor && dividendo%divisor==0){
                a=dividendo.toString()
                b=divisor.toString()
                operation="/"
                cuenta.text=a+" "+operation+" "+b
                break
            }
        }
    }
    private fun enviarResult() {

        val res: Int?
        if (result.text.toString().isNotEmpty()){
            res=Integer.parseInt(result.text.toString())
            when (operation) {
                "+" -> if (res == a.toInt() + b.toInt()) {
                    correctoylimpiar()
                    progressBar.incrementProgressBy(10)
                    createSuma()
                    } else {
                    incorrectoylimpiar()
                    createSuma()
                    }
                "-" -> if (res == a.toInt() - b.toInt()) {
                    correctoylimpiar()
                    progressBar.incrementProgressBy(10)
                    createResta()
                    } else {
                    incorrectoylimpiar()
                    createResta()

                    }
                "/" -> if(res == a.toInt()/b.toInt()){
                    correctoylimpiar()
                    progressBar.incrementProgressBy(10)
                    createDivision()
                    }else{
                    incorrectoylimpiar()
                    createDivision()
                    }
                "x" -> if(res == a.toInt()*b.toInt()){
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