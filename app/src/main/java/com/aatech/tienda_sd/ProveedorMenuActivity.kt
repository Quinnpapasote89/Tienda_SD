package com.aatech.tienda_sd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ProveedorMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proveedor_menu)

        val btnGenOrd = findViewById<Button>(R.id.btnCrearOrden)
        val btnConsulOrd = findViewById<Button>(R.id.btnConsultaOrdeen)
        val btnAltaProv = findViewById<Button>(R.id.btnAltaProveedor)
        val btnConsulProv = findViewById<Button>(R.id.btnConsultaProveedor)

        btnGenOrd.setOnClickListener {
            val intent = Intent(this,GenerarOrdenActivity::class.java)
            startActivity(intent)
        }

        btnConsulOrd.setOnClickListener {
            val intent = Intent(this,ConsultaOrdenesActivity::class.java)
            startActivity(intent)
        }

        btnAltaProv.setOnClickListener {
            val intent = Intent(this,AltaProveedor::class.java)
            startActivity(intent)
        }

        btnConsulProv.setOnClickListener {
            val intent = Intent(this,ConsultaProveedor::class.java)
            startActivity(intent)
        }
    }
}