package com.aatech.tienda_sd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductosActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<AdaptadorProductos.ViewHolder>?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        layoutManager = LinearLayoutManager(this)

        var vistaReciclada = findViewById<RecyclerView>(R.id.productocard)

        vistaReciclada.layoutManager = layoutManager

        adapter = AdaptadorProductos()
        vistaReciclada.adapter = adapter
    }
}