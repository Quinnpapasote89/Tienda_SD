package com.aatech.tienda_sd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val txtRegister = findViewById<TextView>(R.id.txtRegister)

        btnLogin.setOnClickListener {
            abrirMenu()
        }

        txtRegister.setOnClickListener {
            abrirRegister()
        }

    }

    private fun abrirMenu(){
        val intent = Intent(this,ProductosActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    private fun abrirRegister(){
        val intent = Intent(this,RegisterActivity::class.java)
        startActivity(intent)
        this.finish()
    }

}