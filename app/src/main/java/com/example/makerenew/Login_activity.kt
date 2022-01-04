package com.example.makerenew

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Login_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        this.supportActionBar?.hide()

        lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth
        auth = Firebase.auth

        val et_numbers = findViewById<EditText>(R.id.et_loginNumber)
        val et_passwd = findViewById<EditText>(R.id.et_liginPasswd)
        val btn_login = findViewById<Button>(R.id.btn_login)

        val btn_signup = findViewById<Button>(R.id.btn_signup)
        var passwd = ""
        var phoneNumber = ""

        btn_signup.setOnClickListener(){
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }


        btn_login.setOnClickListener(){
//            val intent = Intent(this, Signup::class.java)
//            startActivity(intent)
        phoneNumber = "+82"+et_numbers.text.toString()+"@user.com"
        passwd = et_passwd.text.toString()

            auth.signInWithEmailAndPassword(phoneNumber, passwd)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "로그인 성공")
                        Log.d(TAG, ""+auth.currentUser)
                        val user = auth.currentUser
//                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
//                        updateUI(null)
                    }
                }


        }



    }
}