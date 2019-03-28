package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    var login:EditText?=null
    var repoButton:Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login=findViewById(R.id.login)
        repoButton=findViewById(R.id.repoButton)
        repoButton?.setOnClickListener {
            getRepos()
        }
    }
    fun getRepos(){
        val intent = Intent(this, Repos::class.java)
        intent.putExtra("login", login?.text.toString())
        startActivity(intent)
    }



}
