package com.example.actividad02.views
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.actividad02.R
import com.example.actividad_02.logic.Controller
import com.example.actividad_02.views.Dialog

class MainActivity : AppCompatActivity() {

    companion object {
        const val LOG_TAG = "=== REGISTRO_APP ==="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dialog = Dialog()

        val controller = Controller(this, dialog)

        controller.start()
    }
}