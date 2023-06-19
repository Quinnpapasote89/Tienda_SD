package com.aatech.tienda_sd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import okhttp3.*
import java.io.IOException

class AltaCategoriaActivity : AppCompatActivity() {

    var client: OkHttpClient = OkHttpClient()
    var postUrl = "http://192.168.218.135:9005/sd/nuevaCategoria"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alta_categoria)

        var nombreCategoria = findViewById<EditText>(R.id.editTxtNomCat)
        var descripcionCategoria = findViewById<EditText>(R.id.editTxtDesCat)
        var botonAlt = findViewById<Button>(R.id.btnRegistrarCateg)

        botonAlt.setOnClickListener  {
            postCategoria(nombreCategoria.text.toString(),descripcionCategoria.text.toString())
        }

    }


    fun postCategoria(nom:String, desc:String ){
        var requestBody = FormBody.Builder()
            .add("nombreCategoria",nom)
            .add("descripcionCategoria",desc)
            .build()

        var request = Request.Builder().url(postUrl).post(requestBody).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                Log.println(Log.ASSERT,"",response.toString())
            }
        })
    }

}