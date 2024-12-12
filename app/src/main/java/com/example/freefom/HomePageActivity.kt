package com.example.freefom

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class HomePageActivity : AppCompatActivity() {

    private lateinit var firstName: TextView
    private lateinit var lastName: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.home_page)

        val fName = intent.getStringExtra("first_name")
        val lName = intent.getStringExtra("last_name")

        firstName = findViewById(R.id.firstNameTxt)
        lastName = findViewById(R.id.lastNameTxt)
        firstName.text = fName
        lastName.text = lName








    }
}