package com.aatech.tienda_sd

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.aatech.tienda_sd.objetos.Categoria
import com.aatech.tienda_sd.objetos.Cliente

class CategoriaProductoAdapter(context: Context, clientes: ArrayList<Categoria>) :
    ArrayAdapter<Categoria>(context, 0, clientes) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)

        val categoria: Categoria? = getItem(position)

        val nombreTextView: TextView = view.findViewById(android.R.id.text1)
        nombreTextView.text = categoria?.nombre

        return view
    }
}