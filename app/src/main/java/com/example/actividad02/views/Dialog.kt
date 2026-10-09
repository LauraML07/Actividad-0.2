package com.example.actividad_01.views

import com.example.actividad_01.logic.interfaz.OperacionesCrud

class Dialog {
    private var listener: OperacionesCrud? = null

    fun setListener(listener: OperacionesCrud) {
        this.listener = listener
    }

    fun show(actionType: Int) {
        when (actionType) {
            0 -> {
                val randomId = (10..99).random()
                listener?.clientAdd(randomId, "Laura", "Martínez", "654112233")
            }
            1 -> {
                val targetId = (1..3).random()
                listener?.clientUpdate(targetId, "Laura Edit", "García", "677889900")
            }
            2 -> {
                val targetId = (1..3).random()
                listener?.clientDel(targetId)
            }
        }
    }
}