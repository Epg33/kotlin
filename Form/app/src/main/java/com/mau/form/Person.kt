package com.mau.form

class Person {
    constructor(cedula: String, nombre: String, apellido: String, telefono: String,  edad: Int, sueldo: Int){
        this.cedula = cedula
        this.nombre = nombre
        this.apellido = apellido
        this.telefono = telefono
        this.edad = edad
        this.sueldo = sueldo
    }
    constructor(){
    }
    var id:Int = 0;
    var nombre:String = ""
    var apellido:String = ""
    var telefono:String = ""
    var cedula:String = ""
    var edad:Int = 0
    var sueldo:Int = 0
}