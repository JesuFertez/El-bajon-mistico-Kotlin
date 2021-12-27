package com.jesufertez.elbajnmstico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jesufertez.elbajnmstico.data.ProductoRepository
import com.jesufertez.elbajnmstico.data.remote.ProductosApi


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}