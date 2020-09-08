package com.example.mentalmath

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_suma.setOnClickListener(this)
        btn_confirm.setOnClickListener(this)
        btn_resta.setOnClickListener(this)
        createSuma()



    }

    override fun onClick(v: View) {
        when (v!!.id){
            R.id.btn_resta -> createResta()
            R.id.btn_suma -> createSuma()
            R.id.btn_confirm -> enviarResult()
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

    private fun enviarResult(){
        val num1: Int =  Integer.parseInt(numOne.text.toString())
        val num2: Int = Integer.parseInt(numtwo.text.toString())
        val signo=signed.text.toString()
        val res: Int = Integer.parseInt(result.text.toString())
        when(signo){
            "+" -> if (res == num1 + num2) {
                Toast.makeText(this, "si verdader", Toast.LENGTH_SHORT).show()
                result.text.clear()
                createSuma()
            } else {
                Toast.makeText(this, "incorrecto", Toast.LENGTH_SHORT).show()
                result.text.clear()
                createSuma()
                    }
            "-" -> if (res == num1 - num2) {
                Toast.makeText(this, "correcto", Toast.LENGTH_SHORT).show()
                createResta()
                result.text.clear()
            } else {
                Toast.makeText(this, "incorrecto", Toast.LENGTH_SHORT).show()
                createResta()
                result.text.clear()
            }
        }

    }


}