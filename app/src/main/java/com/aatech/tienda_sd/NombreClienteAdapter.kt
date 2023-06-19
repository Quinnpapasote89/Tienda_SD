package com.aatech.tienda_sd
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.aatech.tienda_sd.objetos.Cliente

class NombreClienteAdapter(context: Context, clientes: ArrayList<Cliente>) :
    ArrayAdapter<Cliente>(context, 0, clientes) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.cliente_item, parent, false)

        val cliente = getItem(position)

        val nombreTextView: TextView = view.findViewById(R.id.nombreTextView)
        val correoTextView: TextView = view.findViewById(R.id.correoTextView)
        val direccionTextView: TextView = view.findViewById(R.id.direccionTextView)
        val telefonoTextView: TextView = view.findViewById(R.id.telefonoTextView)

        nombreTextView.text = cliente?.nombre
        correoTextView.text = cliente?.correoElectronico
        direccionTextView.text = cliente?.direccion
        telefonoTextView.text = cliente?.telefono

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false)

        val cliente: Cliente? = getItem(position)

        val nombreTextView: TextView = view.findViewById(android.R.id.text1)
        nombreTextView.text = cliente?.nombre

        return view
    }
}

