package com.aatech.tienda_sd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.aatech.tienda_sd.objetos.Cliente
import okhttp3.*
import java.io.IOException

class AltaCliente : AppCompatActivity() {
    var client:OkHttpClient = OkHttpClient()
    var postUrl = "http://192.168.218.135:9005/sd/nuevoCliente2"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alta_cliente)

        var nombreCliente = findViewById<EditText>(R.id.editNombreClie)
        var direccionCliente = findViewById<EditText>(R.id.editDirClie)
        var telefonoCliente = findViewById<EditText>(R.id.editPhoneClie)
        var correoElectronico = findViewById<EditText>(R.id.editEmailClie)
        var botonAlt = findViewById<Button>(R.id.btnAltaClie)

        botonAlt.setOnClickListener  {
            postCliente(nombreCliente.text.toString(),direccionCliente.text.toString(),
            telefonoCliente.text.toString(),correoElectronico.text.toString())
        }


    }

    fun postCliente(nom:String, dir:String,tel:String,corr:String ){
        var requestBody = FormBody.Builder()
            .add("nombreCliente",nom)
            .add("direccionCliente",dir)
            .add("telefonoCliente",tel)
            .add("emailCliente",corr)
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