package com.example.freefom

import java.sql.ResultSet

class LoginUser {

    lateinit var con: Database

    fun Login (email: String, password: String){

        val resultSet: ResultSet

        con.connectToDatabase().use { conn ->
            val statement = conn.createStatement()
            resultSet = statement.executeQuery("SELECT * FROM users WHERE email = '$email' AND password = '$password'")
            //resultSet = statement.executeQuery("SELECT * FROM users")

        }

    }

}