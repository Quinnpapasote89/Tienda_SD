package com.aatech.tienda_sd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.aatech.tienda_sd.objetos.Categoria
import okhttp3.*
import java.io.IOException

class AltaProveedor : AppCompatActivity() {
    var client: OkHttpClient = OkHttpClient()
    var postUrl = "http://192.168.218.135:9005/sd/nuevoProveedor"

    lateinit var textoNomProv: EditText
    lateinit var textoDirProv: EditText
    lateinit var textoTelProv: EditText
    lateinit var textoEmailProv: EditText
    lateinit var textoDepaProv: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alta_proveedor)


        textoNomProv = findViewById(R.id.editTxtProvNom)
        textoDirProv = findViewById(R.id.editTxtDirProv)
        textoTelProv = findViewById(R.id.editTxtPhoneProv)
        textoEmailProv = findViewById(R.id.edtiTxtMailProv)
        textoDepaProv = findViewById(R.id.editTxtDepProv)
        var botonAlt = findViewById<Button>(R.id.btnAltaProv)

        botonAlt.setOnClickListener  {
            postProveedor()
        }
    }



    fun postProveedor( ){
        var requestBody = FormBody.Builder()
            .add("nombreProveedor",textoNomProv.text.toString())
            .add("direccionProveedor",textoDirProv.text.toString())
            .add("telefonoProveedor",textoTelProv.text.toString())
            .add("emailProveedor",textoEmailProv.text.toString())
            .add("departamentoProveedor", textoDepaProv.text.toString())
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