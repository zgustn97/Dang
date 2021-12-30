package com.example.makerenew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Login_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        this.supportActionBar?.hide()

        val et_numbers = findViewById<EditText>(R.id.et_loginNumber)
        val et_passwd = findViewById<EditText>(R.id.et_liginPasswd)
        val btn_login = findViewById<Button>(R.id.btn_login)

        val btn_signup = findViewById<Button>(R.id.btn_signup)

        btn_signup.setOnClickListener(){
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }




    }
}