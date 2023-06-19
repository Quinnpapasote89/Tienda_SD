package com.aatech.tienda_sd

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        var imgVentas = findViewById<ImageView>(R.id.imgVentas)
        var imgProveedor = findViewById<ImageView>(R.id.imgProveedor)
        var imgCliente = findViewById<ImageView>(R.id.imgClientes)
        var imgProducto = findViewById<ImageView>(R.id.imgProductos)


        imgVentas.setOnClickListener {
            abrirMenuVentas()
        }

        imgProveedor.setOnClickListener {
            abrirMenuProveedores()
        }

        imgCliente.setOnClickListener {
            abrirMenuClientes()
        }

        imgProducto.setOnClickListener {
            abrirMenuProductos()
        }
    }

    fun abrirMenuVentas () {
        val intent = Intent(this,VentasMenuActivity::class.java)
        startActivity(intent)
    }

    fun abrirMenuProveedores () {
        val intent = Intent(this,ProveedorMenuActivity::class.java)
        startActivity(intent)
    }

    fun abrirMenuClientes () {
        val intent = Intent(this,ClientesMenuAcitivity::class.java)
        startActivity(intent)
    }

    fun abrirMenuProductos () {
        val intent = Intent(this,ProductoMenuActivity::class.java)
        startActivity(intent)
    }
}