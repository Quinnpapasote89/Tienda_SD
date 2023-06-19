package com.aatech.tienda_sd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class AdaptadorProductos : RecyclerView.Adapter <AdaptadorProductos.ViewHolder> (){

    private var nomProd = arrayOf("Laptop", "Celular", "Audifonos")
    private var detalleProd = arrayOf("Una excelente computadora gaming", "Un potente Snapdragon",
        "El mejor sonido del mercado")
    private var precioProd = arrayOf("$18,000", "$7,500","$1,200")
    var image = R.drawable.logo_sandesc


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorProductos.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent,false)
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var productoImage: ImageView
        var nombreProducto: TextView
        var descripcionProducto: TextView
        var precioProducto: TextView

        init{
            productoImage = itemView.findViewById(R.id.imgProducto)
            nombreProducto = itemView.findViewById(R.id.txtNomProd)
            descripcionProducto = itemView.findViewById(R.id.txtDescProd)
            precioProducto = itemView.findViewById(R.id.txtPrecProd)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nombreProducto.text = nomProd[position]
        holder.descripcionProducto.text = detalleProd[position]
    }

    override fun getItemCount(): Int {
        return nomProd.size
    }
}