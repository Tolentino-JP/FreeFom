//package com.example.freefom
//
//
//
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.remember
//import io.github.jan.supabase.createSupabaseClient
//import io.github.jan.supabase.postgrest.Postgrest
//import io.github.jan.supabase.postgrest.from
//import io.github.jan.supabase.postgrest.query.Columns
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.runBlocking
//import kotlinx.coroutines.withContext
//import kotlinx.serialization.Serializable
//
//
//
//class Database {
//
//    val supabase = createSupabaseClient(
//        supabaseUrl = "https://cqvadupqmvxnjoquzjaw.supabase.co",
//        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNxdmFkdXBxbXZ4bmpvcXV6amF3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzMyNDA1NzcsImV4cCI6MjA0ODgxNjU3N30.Px2zFSKfjlPWgeIkduz25tp5Cw_y6DM35v7AjDNcQ1I"
//    ) {
//        install(Postgrest)
//        //install other modules
//    }
//
//}
//
//@Serializable
//data class User(
//    val id: Int,
//    val fname: String,
//    val lname: String,
//    val email: String,
//    val password: String
//)
//
//@Composable
//fun UserData(){
//
//    val user = remember { mutableStateListOf<User>()}
//    LaunchedEffect(Unit){
//        withContext(Dispatchers.IO){
//            val results = supabase.from("user").select().decodeList<User>()
//            user.addAll(results)
//        }
//    }
//    LazyColumn{
//        items(user){
//            users -> ListItem(headlineContent = {Text(text = users.body ) })
//        }
//    }
//
//}
//

package com.example.freefom

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import java.sql.Connection
import java.sql.DriverManager


public class Database{

    val url = "jdbc:postgresql://aws-0-us-east-1.pooler.supabase.com:6543/postgres"
    val user = "postgres.ndpjluvxxbpdmggsmjma"
    val password = "Paul0509"



    fun connectToDatabase() {
        val connection: Connection?

        connection = DriverManager.getConnection(url, user, password)
//        println("Connected to the database successfully!")
////        connection.use {
////            val statement = it.createStatement()
////            val resultSet = statement.executeQuery("SELECT * FROM users")
////
////            while (resultSet.next()) {
////                println("ID: ${resultSet.getInt("id")}, Name: ${resultSet.getString("first_name")}")
//            }
//        }
    }


    fun main(){
        connectToDatabase()
    }

}
