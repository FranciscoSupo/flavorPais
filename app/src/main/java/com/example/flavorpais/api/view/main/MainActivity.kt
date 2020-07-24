package com.example.flavorpais.api.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.flavorpais.R
//import kotlinx.android.synthetic.mexico.activity_main.*
//import kotlinx.android.synthetic.peru.activity_main.*


class MainActivity : AppCompatActivity() {


    private var btnLlamada: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLlamada = findViewById(R.id.btnllamada)

        btnLlamada?.setOnClickListener {
            intent = Intent(applicationContext, llamadaServicio::class.java)
            startActivity(intent)
        }

    }
}