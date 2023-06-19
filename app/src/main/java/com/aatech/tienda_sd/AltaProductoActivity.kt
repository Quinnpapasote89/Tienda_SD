package com.aatech.tienda_sd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.aatech.tienda_sd.objetos.Categoria
import com.aatech.tienda_sd.objetos.Cliente
import com.aatech.tienda_sd.objetos.Producto
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class AltaProductoActivity : AppCompatActivity() {

    var client: OkHttpClient = OkHttpClient()
    var postUrl = "http://192.168.218.135:9005/sd/nuevoProducto"
    var getUrl = "http://192.168.218.135:9005/sd/categorias"
    val listaCategoria = ArrayList<Categoria>()
    lateinit var categoriaSeleccionada:Categoria
    var categoria:Int = 0
    lateinit var textoNomProd: EditText
    lateinit var textoDescProd: EditText
    lateinit var textoPrecVenta: EditText
    lateinit var textoPrecCompra: EditText
    lateinit var textoStockIni: EditText
    lateinit var textoStockMin: EditText
    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alta_producto)

        var btnRegProd = findViewById<Button>(R.id.btnAltaProd)
        textoNomProd = findViewById(R.id.edtiNomProd)
        textoDescProd = findViewById(R.id.edtiDescProd)
        textoPrecVenta = findViewById(R.id.edtiPrecioVentaProd)
        textoPrecCompra = findViewById(R.id.edtiPrecCompraPro)
        textoStockIni = findViewById(R.id.editStockInicial)
        textoStockMin = findViewById(R.id.edtiStockMínimo)


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
                        val id = item.getInt("id")

                        val categoria = Categoria(id, nombre, descripcion)
                        listaCategoria.add(categoria)
                    }


                    val adapter = CategoriaProductoAdapter(this@AltaProductoActivity,listaCategoria)
                    spinner = findViewById(R.id.spinCatProdAlta)
                    spinner.adapter = adapter

                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            categoriaSeleccionada = spinner.getItemAtPosition(position) as Categoria

                            if(categoriaSeleccionada != null){
                                categoria = categoriaSeleccionada.id
                            }


                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            // Implementación opcional si deseas realizar alguna acción cuando no se selecciona nada
                        }
                    }
                }
            }

        })

        btnRegProd.setOnClickListener {
            altaProducto()
        }
    }

    fun altaProducto(){
        var requestBody = FormBody.Builder()
            .add("nombreProducto",textoNomProd.text.toString())
            .add("descripcionCategoria",textoDescProd.text.toString())
            .add("precioVenta",textoPrecVenta.text.toString())
            .add("precioCompra",textoPrecCompra.text.toString())
            .add("cantidadStock",textoStockIni.text.toString())
            .add("cantidadMinima",textoStockMin.text.toString())
            .add("categoria",categoria.toString())
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