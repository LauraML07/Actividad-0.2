package com.example.actividad02.logic

import android.util.Log
import android.widget.ImageView
import com.example.actividad02.R
import com.example.actividad02.data.RepositorioCliente
import com.example.actividad02.views.Dialog
import com.example.actividad02.views.MainActivity

class Controller(private val activity: MainActivity) {

    private val clientList: MutableList<Cliente> = RepositorioCliente.listClients.toMutableList()

    private val dialog = Dialog(
        onAddCustomer = { id, name, surname, phone ->
            val nuevo = Cliente(id, name, surname, phone)
            clientList.add(nuevo)
            Log.d(MainActivity.LOG_TAG, "[LAMBDA] Cliente insertado -> ID: $id | $name $surname | Tel: $phone")
            printConsoleData()
        },
        onUpdateCustomer = { id, name, surname, phone ->
            val cliente = clientList.find { it.id == id }
            if (cliente != null) {
                cliente.name = name
                cliente.surname = surname
                cliente.phone = phone
                Log.d(MainActivity.LOG_TAG, "[LAMBDA] Cliente modificado -> ID: $id | $name $surname | Tel: $phone")
            } else {
                Log.d(MainActivity.LOG_TAG, "[LAMBDA] No encontrado ID: $id para modificar")
            }
            printConsoleData()
        },
        onDeleteCustomer = { id ->
            val borrado = clientList.removeIf { it.id == id }
            if (borrado) {
                Log.d(MainActivity.LOG_TAG, "[LAMBDA] Cliente con ID: $id eliminado")
            } else {
                Log.d(MainActivity.LOG_TAG, "[LAMBDA] No encontrado ID: $id para eliminar")
            }
            printConsoleData()
        }
    )

    fun start() {
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

    private fun printConsoleData() {
        val sb = StringBuilder()
        sb.append("\n--- LISTA ACTUAL DE CLIENTES (${clientList.size}) ---\n")
        clientList.forEach {
            sb.append("ID: ${it.id} | ${it.name} ${it.surname} | Tel: ${it.phone}\n")
        }
        Log.d(MainActivity.LOG_TAG, sb.toString())
    }
}