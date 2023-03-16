package com.mau.sqlite_prueba

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos (contexto:Context):SQLiteOpenHelper(contexto, "Factura", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "create table Productos(id integer primary key AUTOINCREMENT, name varchar(80) not null, cantidad integer not null)";
        db?.execSQL(sql);
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun Guardar(producto: Producto) : String{
        val db = this.writableDatabase;
        var contenedor = ContentValues();
        contenedor.put("name", producto.name)
        contenedor.put("cantidad", producto.cantidad)
        try {
            var resultado = db.insert("Productos", null, contenedor);

            if (resultado == (-1).toLong()) {
                return "Hubo un error al guardar";
            }
            return "se guardo correctamente";
        }catch (ex:Exception){
            return ex.message.toString()

        }finally {
            db.close();
        }
    }


    fun Actualizar(cantidad:String, id:Int, nombre:String) : String{
        val db = this.writableDatabase;
        var contenedor = ContentValues();
        contenedor.put("name", nombre)
        contenedor.put("cantidad", cantidad)
        try{
            var resultado = db.update("Productos", contenedor,"id=?", arrayOf(id.toString()));

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

    fun Actualiza2r(producto: Producto) : String{
        val db = this.writableDatabase;
        var contenedor = ContentValues();
        contenedor.put("name", producto.name)
        contenedor.put("cantidad", producto.cantidad)
        try{
            var resultado = db.update("Productos", contenedor,"id=?", arrayOf(producto.id.toString()));

            if (resultado > 0  ){
                return "Se actualizó correctamente"
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
            var resultado = db.delete("Productos", "id=?", arrayOf(id.toString()))

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
}