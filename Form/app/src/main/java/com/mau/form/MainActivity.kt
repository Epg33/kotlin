package com.mau.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var id:EditText
    lateinit var nombre:EditText
    lateinit var apellido:EditText
    lateinit var telefono:EditText
    lateinit var cedula:EditText
    lateinit var edad:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}