package com.aatech.tienda_sd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.aatech.tienda_sd.objetos.Cliente
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class ModificarClienteActivity : AppCompatActivity() {
    var client:OkHttpClient = OkHttpClient()
    var postUrl = "http://192.168.218.135:9005/sd/modificarCliente"
    var getUrl = "http://192.168.218.135:9005/sd/clientes"
    var arrayNames = ArrayList<String>()
    val clientList = ArrayList<Cliente>()
    var idCliente:Int = 0
    lateinit var textoNombre: EditText
    lateinit var textoDireccion:EditText
    lateinit var textoTelefono:EditText
    lateinit var textoEmail:EditText
    lateinit var spinner: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_cliente)

        textoNombre = findViewById(R.id.editModNomClie)
        textoDireccion = findViewById(R.id.editModDirClie)
        textoTelefono = findViewById(R.id.editModPhoneClie)
        textoEmail = findViewById(R.id.editModEmailClie)

        var btnModificar = findViewById<Button>(R.id.btnModCliente)

        btnModificar.setOnClickListener {
            modificarCliente()
        }


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
                    for (nombre in arrayNames) {
                        Log.println(Log.ERROR,"ll",nombre)
                    }

                    val adapter = NombreClienteAdapter(this@ModificarClienteActivity,clientList)
                    spinner = findViewById(R.id.spinnModCliente)
                    spinner.adapter = adapter

                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            val clienteSeleccionado = spinner.getItemAtPosition(position) as Cliente


                            // Realizar acciones con los campos del objeto Cliente
                            if (clienteSeleccionado != null) {
                                idCliente = clienteSeleccionado.id
                                textoNombre.setText(clienteSeleccionado.nombre)
                                textoDireccion.setText(clienteSeleccionado.direccion)
                                textoTelefono.setText(clienteSeleccionado.telefono)
                                textoEmail.setText(clienteSeleccionado.correoElectronico)

                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            // Implementación opcional si deseas realizar alguna acción cuando no se selecciona nada
                        }
                    }
                }
            }

        })
    }

    fun modificarCliente(){
        var requestBody = FormBody.Builder()
            .add("idCliente",idCliente.toString())
            .add("nombreCliente",textoNombre.text.toString())
            .add("direccionCliente",textoDireccion.text.toString())
            .add("telefonoCliente",textoTelefono.text.toString())
            .add("emailCliente",textoEmail.text.toString())
            .build()

        var request = Request.Builder().url(postUrl).post(requestBody).build()
        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                Log.println(Log.ASSERT,"",response.toString())
            }
        })



    }


}