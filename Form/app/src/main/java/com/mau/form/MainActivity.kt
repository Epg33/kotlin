package com.mau.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    lateinit var id:EditText
    lateinit var nombre:EditText
    lateinit var apellido:EditText
    lateinit var telefono:EditText
    lateinit var cedula:EditText
    lateinit var edad:EditText
    lateinit var sueldo:EditText
    lateinit var listD:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cedula=findViewById(R.id.txtCedula)
        nombre=findViewById(R.id.txtNombre)
        apellido=findViewById(R.id.txtApellido)
        edad=findViewById(R.id.txtEdad)
        telefono=findViewById(R.id.txtTelefono)
        sueldo=findViewById(R.id.txtSueldo)
        id=findViewById(R.id.txtId)
        listD=findViewById(R.id.txtData)
    }

    fun SaveData(view: View) {
        var db = DB(this)
        var person = Person()
        if(nombre.text.toString().isNotEmpty() && apellido.text.toString().isNotEmpty() && telefono.text.toString().isNotEmpty() && cedula.text.toString().isNotEmpty() && edad.text.toString().isNotEmpty() && sueldo.text.toString().isNotEmpty()){
            person.cedula = cedula.text.toString()
            person.nombre = nombre.text.toString()
            person.apellido = apellido.text.toString()
            person.edad = edad.text.toString().toInt()
            person.telefono = telefono.text.toString()
            person.sueldo = sueldo.text.toString().toInt()
            var message = db.Guardar(person)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    fun showData(view: View){
        listD.text=""
        var db = DB(this)
        var data = db.listar()
        if(data.isNotEmpty()){
            for (i in 0..data.size-1){
                listD.append("id "+data.get(i).id+" cedula "+data.get(i).cedula+" nombre "+data.get(i).nombre+" apellido "+data.get(i).apellido+" edad "+data.get(i).edad+" telefono "+data.get(i).telefono+" sueldo "+data.get(i).sueldo)
            }
        }
    }

    fun EraseData(view: View){
        var db = DB(this)
        if (id.text.isNotEmpty()){
            db.Eliminar(id.text.toString().toInt())
        }
    }

    fun updateData(view:View) {
        listD.text=""
        var db = DB(this)
        var person = Person()
        if(nombre.text.toString().isNotEmpty() && apellido.text.toString().isNotEmpty() && telefono.text.toString().isNotEmpty() && cedula.text.toString().isNotEmpty() && edad.text.toString().isNotEmpty() && sueldo.text.toString().isNotEmpty()) {
            person.cedula = cedula.text.toString()
            person.nombre = nombre.text.toString()
            person.apellido = apellido.text.toString()
            person.edad = edad.text.toString().toInt()
            person.telefono = telefono.text.toString()
            person.sueldo = sueldo.text.toString().toInt()
            var message = db.Actualizar(person.cedula, id.text.toString().toInt(), person.nombre, person.apellido, person.telefono, person.edad, person.sueldo)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}