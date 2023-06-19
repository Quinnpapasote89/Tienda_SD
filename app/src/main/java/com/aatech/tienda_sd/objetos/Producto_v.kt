package com.aatech.tienda_sd.objetos

import java.sql.Timestamp

class Producto_v  (val nombre:String, val descripcion:String, val precio_venta:String,
                   val precio_compra: String, val cantidad_stock:String, val cantidad_minima:String,
                   val categoria:String, val fecha: String){}