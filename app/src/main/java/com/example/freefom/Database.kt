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
