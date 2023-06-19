package com.aatech.tienda_sd

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.aatech.tienda_sd.objetos.Cliente
import com.aatech.tienda_sd.objetos.Producto_v

class ProductoSpinActivity(context: Context, clientes: ArrayList<Producto_v>) :
    ArrayAdapter<Producto_v>(context, 0, clientes) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)

        val producto: Producto_v? = getItem(position)

        val nombreTextView: TextView = view.findViewById(android.R.id.text1)
        nombreTextView.text = producto?.nombre

        return view
    }
}