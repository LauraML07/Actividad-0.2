package com.example.actividad_01.logic

import android.util.Log
import android.widget.ImageView
import com.example.actividad02.R
import com.example.actividad02.data.RepositorioCliente
import com.example.actividad_01.logic.interfaz.OperacionesCrud
import com.example.actividad_01.views.Dialog
import com.example.actividad_01.views.MainActivity

class Controller(
    private val activity: MainActivity,
    private val dialog: Dialog
) : OperacionesCrud {

    private val clientList: MutableList<Cliente> = RepositorioCliente.listClients.toMutableList()

    fun start() {
        // Asignamos este Controller como listener del Dialog
        dialog.setListener(this)

        // Configuramos la interacción de los botones de la vista
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
        Log.d(MainActivity.LOG_TAG, "Cliente insertado -> ID: $id | $name $surname | Tel: $phone")
        printConsoleData()
    }

    override fun clientUpdate(id: Int, name: String, surname: String, phone: String) {
        val cliente = clientList.find { it.id == id }
        if (cliente != null) {
            cliente.name = name
            cliente.surname = surname
            cliente.phone = phone
            Log.d(MainActivity.LOG_TAG, "Cliente modificado -> ID: $id | $name $surname | Tel: $phone")
        } else {
            Log.d(MainActivity.LOG_TAG, "No se encontró cliente con ID: $id para modificar")
        }
        printConsoleData()
    }

    override fun clientDel(id: Int) {
        val borrado = clientList.removeIf { it.id == id }
        if (borrado) {
            Log.d(MainActivity.LOG_TAG, "Cliente con ID: $id eliminado con éxito")
        } else {
            Log.d(MainActivity.LOG_TAG, "No se encontró cliente con ID: $id para eliminar")
        }
        printConsoleData()
    }

    private fun printConsoleData() {
        val sb = StringBuilder()
        sb.append("\n--- LISTA ACTUAL DE CLIENTES (${clientList.size}) ---\n")
        clientList.forEach {
            sb.append("ID: ${it.id} | ${it.name} ${it.surname} | Tel: ${it.phone}\n")
        }
        Log.d(MainActivity.LOG_TAG, sb.toString())
    }
}