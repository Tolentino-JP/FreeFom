package com.example.freefom

import java.sql.Connection
import java.sql.DriverManager


public class Database{

    val url = "jdbc:postgresql://aws-0-us-east-1.pooler.supabase.com:6543/postgres"
    val user = "postgres.ndpjluvxxbpdmggsmjma"
    val password = "Paul0509"



    fun connectToDatabase(): Connection {
        val connection: Connection

        connection = DriverManager.getConnection(url, user, password)
        return connection
//        println("Connected to the database successfully!")
////        connection.use {
////            val statement = it.createStatement()
////            val resultSet = statement.executeQuery("SELECT * FROM users")
////
////            while (resultSet.next()) {
////                println("ID: ${resultSet.getInt("id")}, Name: ${resultSet.getString("first_name")}")
//            }
//        }
    }// end function


//    fun main(){
//        connectToDatabase()
//    }

}