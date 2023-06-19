package com.aatech.tienda_sd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class VentasMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ventas_menu)

        var botonAlta = findViewById<Button>(R.id.btnAltaProducto)
        var consultaVenta = findViewById<Button>(R.id.btnConsultaProductos)

        botonAlta.setOnClickListener {
            val intent = Intent(this,GenerarVenta::class.java)
            startActivity(intent)
        }

        consultaVenta.setOnClickListener {
            val intent = Intent(this,ConsultaVentasActivity::class.java)
            startActivity(intent)
        }


    }
}