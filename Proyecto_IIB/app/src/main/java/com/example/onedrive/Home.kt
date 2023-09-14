package com.example.onedrive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btn: Button = findViewById (R.id.btn_buscar)
        btn.setOnClickListener {
           val intent: Intent = Intent (this, MainActivity::class.java)
            startActivity(intent)

        }
    }




}