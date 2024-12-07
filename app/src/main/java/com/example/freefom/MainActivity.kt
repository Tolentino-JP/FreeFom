package com.example.freefom

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.sql.Connection

private val con = Database()

class MainActivity : AppCompatActivity() {

    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loginBtn = findViewById(R.id.btnSign)

        loginBtn.setOnClickListener{
            val login = LoginUser()

            val email = findViewById<TextView>(R.id.inputEmail)
            val password = findViewById<TextView>(R.id.inputPassword)
            val errorCred = findViewById<TextView>(R.id.errorTxt)

            if(email.text.isNullOrEmpty() || password.text.isNullOrEmpty()){
                errorCred.visibility = TextView.VISIBLE
            }else{
                login.Login(email.text.toString(), password.text.toString())
            }




        }





    }

//    fun SignIn() {
//
//        con.connectToDatabase().use { conn ->
//            val statement = conn.createStatement()
//            val resultSet = statement.executeQuery("SELECT * FROM users WHERE email = '$email' AND password = '$password'")
//            println(resultSet.next())
//        }
//
//    }

}




