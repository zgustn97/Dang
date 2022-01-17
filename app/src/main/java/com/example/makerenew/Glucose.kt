package com.example.makerenew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Glucose : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glucose)
        this.supportActionBar?.hide()

        val rv_glucose = findViewById<RecyclerView>(R.id.rv_glucose)
        val btn_calander = findViewById<ImageButton>(R.id.btn_calander)
        val btn_graph = findViewById<ImageButton>(R.id.btn_graph)
        val btn_bluetooth = findViewById<ImageButton>(R.id.btn_bluetooth)

        btn_calander.setOnClickListener(){
            val intent = Intent(this, glucose_calander::class.java)
            startActivity(intent)
        }


        val glucoseList = arrayListOf(
            glucoseInfo("기상","7AM","100"),
            glucoseInfo("아침","10AM","110"),
            glucoseInfo("점심","12PM","120"),
            glucoseInfo("저녁","6PM","130"),
            glucoseInfo("취침전","10PM","140")
        )

    rv_glucose.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_glucose.setHasFixedSize(true)

        rv_glucose.adapter = glucoseInfoAdapter(glucoseList)

    }
}