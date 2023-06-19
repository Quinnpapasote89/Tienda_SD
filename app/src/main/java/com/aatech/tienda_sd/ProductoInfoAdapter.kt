package com.aatech.tienda_sd

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.aatech.tienda_sd.objetos.Cliente
import com.aatech.tienda_sd.objetos.Producto_v

class ProductoInfoAdapter(context: Context, producto: ArrayList<Producto_v>) :
    ArrayAdapter<Producto_v>(context, 0, producto) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.product_layout, parent, false)

        val producto = getItem(position)

        val nombreTextView: TextView = view.findViewById(R.id.txtNomProd)
        val correoTextView: TextView = view.findViewById(R.id.txtDescProd)
        val direccionTextView: TextView = view.findViewById(R.id.txtPrecProd)

        nombreTextView.text = producto?.nombre
        correoTextView.text = producto?.descripcion
        direccionTextView.text = producto?.precio_venta

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        TODO()
    }
}