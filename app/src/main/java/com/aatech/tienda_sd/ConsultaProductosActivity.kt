package com.aatech.tienda_sd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.aatech.tienda_sd.objetos.Cliente
import com.aatech.tienda_sd.objetos.Producto
import com.aatech.tienda_sd.objetos.Producto_v
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ConsultaProductosActivity : AppCompatActivity() {

    var client: OkHttpClient = OkHttpClient()
    var getUrl = "http://192.168.218.135:9005/sd/productos"
    val productList = ArrayList<Producto_v>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_productos2)

        var request = Request.Builder().url(getUrl).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                if (responseData != null) {
                    val json = JSONObject(responseData)
                    val strAnswerArray = json.getJSONArray("strAnswer")

                    for (i in 0 until strAnswerArray.length()) {
                        val item = strAnswerArray.getJSONObject(i)
                        val nombre = item.getString("nombre")
                        val descripcion = item.getString("descripcion")
                        val prec_venta = item.getString("precio_venta")
                        val prec_comp = item.getString("precio_compra")
                        val cant_stock = item.getString("cantidad_stock")
                        val cant_min = item.getString("cantidad_minima")
                        val categoria_id = item.getString("categoria_id")
                        val fecha_creacion = item.getString("fecha_creacion")

                        val producto = Producto_v( nombre, descripcion, prec_venta, prec_comp,cant_stock,cant_min,categoria_id,fecha_creacion)
                        productList.add(producto)
                    }

                    // Aquí puedes hacer uso de los nombres almacenados en la lista
                    // Crear un ArrayAdapter para el ListView
                    val adapter = ProductoInfoAdapter(this@ConsultaProductosActivity, productList)
                    // Obtener el ListView desde el diseño
                    val listViewPro: ListView = findViewById(R.id.listViewProd)
                    // Asignar el adaptador al ListView
                    listViewPro.adapter = adapter
                }
            }

        })
    }
}