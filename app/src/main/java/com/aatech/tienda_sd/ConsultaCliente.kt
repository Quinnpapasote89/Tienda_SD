package com.aatech.tienda_sd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.aatech.tienda_sd.objetos.Cliente
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ConsultaCliente : AppCompatActivity() {

    var client: OkHttpClient = OkHttpClient()
    var getUrl = "http://192.168.218.135:9005/sd/clientes"
    val clientList = ArrayList<Cliente>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_cliente)


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
                        val correo = item.getString("correo_electronico")
                        val direccion = item.getString("direccion")
                        val id = item.getInt("id")
                        val telefono = item.getString("telefono")

                        val cliente = Cliente(id, nombre, direccion, telefono, correo)
                        clientList.add(cliente)
                    }

                    // Aquí puedes hacer uso de los nombres almacenados en la lista
                    // Crear un ArrayAdapter para el ListView
                    val adapter = NombreClienteAdapter(this@ConsultaCliente, clientList)

                    // Obtener el ListView desde el diseño
                    val listViewClientes: ListView = findViewById(R.id.listViewClientes)

                    // Asignar el adaptador al ListView
                    listViewClientes.adapter = adapter


                }
            }

        })
    }

}