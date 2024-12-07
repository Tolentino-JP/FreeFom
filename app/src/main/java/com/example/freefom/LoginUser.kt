package com.example.freefom

import java.sql.DriverManager
import java.sql.ResultSet

class LoginUser {

    lateinit var con: Database

    fun Login (email: String, password: String){

        val resultSet: ResultSet

        val connect = DriverManager.getConnection(con.url, con.user, con.password)
        connect.use {
            val statement = connect.createStatement()
            resultSet = statement.executeQuery("SELECT * FROM users WHERE email = '$email' AND password = '$password'")
            //resultSet = statement.executeQuery("SELECT * FROM users")

        }

    }

}