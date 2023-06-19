package com.aatech.tienda_sd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import com.aatech.tienda_sd.objetos.Cliente
import com.aatech.tienda_sd.objetos.Producto_v
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class GenerarVenta : AppCompatActivity() {

    val inicio = 2
    val fin = 1000
    var client: OkHttpClient = OkHttpClient()
    var getUrl = "http://192.168.218.135:9005/sd/clientes"
    var getUrl2 = "http://192.168.218.135:9005/sd/productos"
    var postUrl = "http://192.168.218.135:9005/sd/generarVenta"
    val clientList = ArrayList<Cliente>()
    lateinit var spinner: Spinner
    lateinit var spinner2: Spinner
    lateinit var clienteSeleccionado: Cliente
    lateinit var productoSeleccionado: Producto_v
    lateinit var textoDepartamento: EditText
    lateinit var textoNotas:EditText
    lateinit var textoMetodoPago:EditText
    val productList = ArrayList<Producto_v>()
    val listaprod = ArrayList<Producto_v>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generar_venta)

        textoDepartamento = findViewById(R.id.edtiDepaVenta)
        textoNotas = findViewById(R.id.editTextNotasVenta)
        textoMetodoPago = findViewById(R.id.edtiTxtMetPago)


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


                    val adapter = NombreClienteAdapter(this@GenerarVenta,clientList)
                    spinner = findViewById(R.id.spinClientVent)
                    spinner.adapter = adapter

                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            clienteSeleccionado = spinner.getItemAtPosition(position) as Cliente
                            // Realizar acciones con los campos del objeto Cliente
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            // Implementación opcional si deseas realizar alguna acción cuando no se selecciona nada
                        }
                    }
                }
            }

        })

        request = Request.Builder().url(getUrl2).build()
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
                    val adapter = ProductoSpinActivity(this@GenerarVenta,productList)
                    spinner2 = findViewById(R.id.spinProd)
                    spinner2.adapter = adapter

                    spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            productoSeleccionado = spinner2.getItemAtPosition(position) as Producto_v
                            // Realizar acciones con los campos del objeto Cliente
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            // Implementación opcional si deseas realizar alguna acción cuando no se selecciona nada
                        }
                    }
                }
            }

        })

        var btnAddProd = findViewById<Button>(R.id.btnAddProdVen)
        var btnCrearVenta= findViewById<Button>(R.id.btnCrearVenta)
        var cantidadProd = findViewById<EditText>(R.id.edtiTextCantProd)

        btnAddProd.setOnClickListener {
            listaprod.add(productoSeleccionado)
        }

        btnCrearVenta.setOnClickListener {
            var suma = 0.0
            for(x in listaprod){
                suma += x.precio_venta.toDouble() * cantidadProd.text.toString().toDouble()
            }

            generarVenta(suma)
        }

    }

    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (Math.random() * (end - start + 1)).toInt() + start
    }

    fun generarVenta(suma:Double){
        var requestBody = FormBody.Builder()
            .add("idVenta",rand(inicio,fin).toString())
            .add("clienteId",clienteSeleccionado.id.toString())
            .add("totalVenta",suma.toString())
            .add("ventaDep",textoDepartamento.text.toString())
            .add("ventaNota",textoNotas.text.toString())
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