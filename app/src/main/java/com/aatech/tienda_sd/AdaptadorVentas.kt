package com.aatech.tienda_sd

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aatech.tienda_sd.objetos.Producto

class AdaptadorVentas(val context: Context, val list: ArrayList<Producto>): BaseAdapter (){
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //val view: View = LayoutInflater.from(context).inflate(R.layout.consulta_venta,parent,false)
        TODO()

    }

}