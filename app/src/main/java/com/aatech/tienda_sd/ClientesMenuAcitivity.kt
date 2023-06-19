package com.aatech.tienda_sd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ClientesMenuAcitivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clientes_menu)

        var botonAlta = findViewById<Button>(R.id.btnAltaProducto)
        var botonModificar = findViewById<Button>(R.id.btnModCliente)
        var consultaVenta = findViewById<Button>(R.id.btnConsultaProductos)

        botonAlta.setOnClickListener {
            val intent = Intent(this,AltaCliente::class.java)
            startActivity(intent)
        }

        botonModificar.setOnClickListener {
            val intent = Intent(this,ModificarClienteActivity::class.java)
            startActivity(intent)
        }

        consultaVenta.setOnClickListener {
            val intent = Intent(this,ConsultaCliente::class.java)
            startActivity(intent)
        }
    }
}