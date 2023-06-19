package com.aatech.tienda_sd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ProductoMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto_menu)

        var btnAltaProd = findViewById<Button>(R.id.btnAltaProducto)
        var btnConsProd = findViewById<Button>(R.id.btnConsultaProductos)
        var btnAltaCat = findViewById<Button>(R.id.btnAltaCategorias)

        btnAltaProd.setOnClickListener {
            val intent = Intent(this,AltaProductoActivity::class.java)
            startActivity(intent)
        }

        btnConsProd.setOnClickListener {
            val intent = Intent(this, ConsultaProductosActivity::class.java)
            startActivity(intent)
        }

        btnAltaCat.setOnClickListener {
            val intent = Intent(this, AltaCategoriaActivity::class.java)
            startActivity(intent)
        }
    }
}