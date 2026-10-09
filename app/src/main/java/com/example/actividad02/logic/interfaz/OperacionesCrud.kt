package com.example.actividad_01.logic.interfaz

interface OperacionesCrud {
    fun clientAdd(id: Int, name: String, surname: String, phone: String)
    fun clientUpdate(id: Int, name: String, surname: String, phone: String)
    fun clientDel(id: Int)
}