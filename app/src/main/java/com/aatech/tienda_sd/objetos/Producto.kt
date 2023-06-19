package com.aatech.tienda_sd.objetos

import java.sql.Timestamp

class Producto(val id:Int, val nombre:String, val descripcion:String, val precio_venta:Float,
               val precio_compra: Float, val cantidad_stock:Int, val cantidad_minima:Int,
               val categoria:Int, val fecha:Timestamp){
}