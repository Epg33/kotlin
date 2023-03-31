package com.mau.form

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB (contexto:Context):SQLiteOpenHelper(contexto, "Person", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "create table Person(id integer primary key AUTOINCREMENT, cedula varchar(10) not null, nombre varchar(20) not null, apellido varchar(20) not null, telefono varchar(10) not null, edad integer not null, sueldo integer not null)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun Guardar(persona: Person) : String {
        val db = this.writableDatabase;
        var contenedor = ContentValues();
        contenedor.put("cedula", persona.cedula)
        contenedor.put("nombre", persona.nombre)
        contenedor.put("apellido", persona.apellido)
        contenedor.put("telefono", persona.telefono)
        contenedor.put("edad", persona.edad)
        contenedor.put("sueldo", persona.sueldo)
        try {
            var resultado = db.insert("Person", null, contenedor);

            if (resultado == (-1).toLong()){
                return "Hubo un error al Guardar";
            }
            return "Se guardo correctamente";
        } catch (ex: Exception) {
            return ex.message.toString();
        } finally {
            db.close()
        }
    }

    fun Actualizar(cedula:String, id:Int, nombre:String, apellido:String, telefono: String,  edad: Int, sueldo: Int) : String{
        val db = this.writableDatabase;
        var contenedor = ContentValues();
        contenedor.put("cedula", cedula)
        contenedor.put("nombre", nombre)
        contenedor.put("apellido", apellido)
        contenedor.put("telefono", telefono)
        contenedor.put("edad", edad)
        contenedor.put("sueldo", sueldo)
        try{
            var resultado = db.update("Person", contenedor,"id=?", arrayOf(id.toString()));

            if (resultado > 0  ){
                return "Se actualizó"
            }
            return "Hubo un error";
        }catch (ex:Exception){
            return ex.message.toString();
        }finally {
            db.close();
        }

    }

    fun Eliminar(id:Int): String{
        val db = this.writableDatabase;
        try{
            var resultado = db.delete("Person", "id=?", arrayOf(id.toString()))

            if (resultado > 0){
                return "Se eliminó correctamente"
            }
            return "Hubo un error";
        }catch (ex:Exception){
            return ex.message.toString();
        }finally {
            db.close()
        }
    }

    @SuppressLint("Range")
    fun listar():MutableList<Person>{
        val lista: MutableList<Person> = ArrayList()
        val db = this.readableDatabase
        val sql = "select * from Person"
        var resultado = db.rawQuery(sql, null)
        if (resultado.moveToFirst()) {
            do {
                var datosp=Person();
                datosp.id = resultado.getString(resultado.getColumnIndex("id")).toInt()
                datosp.cedula = resultado.getString(resultado.getColumnIndex("cedula"))
                datosp.nombre = resultado.getString(resultado.getColumnIndex("nombre"))
                datosp.apellido = resultado.getString(resultado.getColumnIndex("apellido"))
                datosp.telefono = resultado.getString(resultado.getColumnIndex("telefono"))
                datosp.edad = resultado.getInt(resultado.getColumnIndex("edad"))
                datosp.sueldo = resultado.getInt(resultado.getColumnIndex("sueldo"))
                lista.add(datosp)
            }while (resultado.moveToNext())
            resultado.close()
            db.close()
        }
        return lista
    }
}