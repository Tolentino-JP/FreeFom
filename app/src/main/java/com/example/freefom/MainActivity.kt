package com.example.freefom

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest



val supabase = createSupabaseClient(
    supabaseUrl = "https://cqvadupqmvxnjoquzjaw.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNxdmFkdXBxbXZ4bmpvcXV6amF3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzMyNDA1NzcsImV4cCI6MjA0ODgxNjU3N30.Px2zFSKfjlPWgeIkduz25tp5Cw_y6DM35v7AjDNcQ1I"
) {
    install(Postgrest)
}


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

}




