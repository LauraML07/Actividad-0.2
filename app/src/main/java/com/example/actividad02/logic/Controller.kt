package com.example.actividad02.logic

import android.util.Log
import android.widget.ImageView
import com.example.actividad02.R
import com.example.actividad02.data.RepositorioCliente
import com.example.actividad02.logic.Cliente
import com.example.actividad02.logic.interfaz.OperacionesCrud
import com.example.actividad02.views.Dialog
import com.example.actividad02.views.MainActivity

class Controller(
    private val activity: MainActivity,
    private val dialog: Dialog
) : OperacionesCrud {

    private val clientList: MutableList<Cliente> = RepositorioCliente.listClients.toMutableList()

    fun start() {
        dialog.setListener(this)

        val btnRegister = activity.findViewById<ImageView>(R.id.myButtonAdd)
        val btnModify = activity.findViewById<ImageView>(R.id.myButtonEdit)
        val btnRemove = activity.findViewById<ImageView>(R.id.myButtonDel)

        btnRegister.setOnClickListener {
            dialog.show(0)
        }

        btnModify.setOnClickListener {
            dialog.show(1)
        }

        btnRemove.setOnClickListener {
            dialog.show(2)
        }
    }

    override fun clientAdd(id: Int, name: String, surname: String, phone: String) {
        val nuevoCliente = Cliente(id, name, surname, phone)
        clientList.add(nuevoCliente)
        Log.d(MainActivity.LOG_TAG, "Cliente añadido: ID $id - $name $surname, Tel: $phone")
        printConsoleData()
    }

    override fun clientUpdate(id: Int, name: String, surname: String, phone: String) {
        val cliente = clientList.find { it.id == id }
        if (cliente != null) {
            cliente.name = name
            cliente.surname = surname
            cliente.phone = phone
            Log.d(MainActivity.LOG_TAG, "Cliente modificado: ID $id")
        } else {
            Log.d(MainActivity.LOG_TAG, "Error: no se encontró al cliente con ID $id")
        }
        printConsoleData()
    }

    override fun clientDel(id: Int) {
        val borrado = clientList.removeIf { it.id == id }
        if (borrado) {
            Log.d(MainActivity.LOG_TAG, "Cliente borrado (ID: $id)")
        } else {
            Log.d(MainActivity.LOG_TAG, "No se pudo borrar, el ID $id no existe")
        }
        printConsoleData()
    }

    private fun printConsoleData() {
        Log.d(MainActivity.LOG_TAG, "--- Total de clientes: ${clientList.size} ---")
        clientList.forEach {
            Log.d(MainActivity.LOG_TAG, "Cliente ${it.id}: ${it.name} ${it.surname} (${it.phone})")
        }
    }
}