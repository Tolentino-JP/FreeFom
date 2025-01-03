package com.example.freefrom

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var loginBtn: Button
    private lateinit var createAccount: TextView
    private lateinit var emailLogin: EditText
    private lateinit var passwordLogin: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        auth = Firebase.auth

        loginBtn = findViewById(R.id.btnSign)
        emailLogin = findViewById(R.id.emailLogin)
        passwordLogin = findViewById(R.id.passwordLogin)
        createAccount = findViewById(R.id.createAccountBtn)

        loginBtn.setOnClickListener{
            if(emailLogin.text.toString().isEmpty() || passwordLogin.text.toString().isEmpty()){
                emailLogin.error = "Enter email"
                passwordLogin.error = "Enter password"
            }else{
                signIn(emailLogin.text.toString(), passwordLogin.text.toString())
            }
        }

        createAccount.setOnClickListener {
            Intent(this, SignUpActivity::class.java).also { startActivity(it) }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Intent(this, MainNavActivity::class.java).also { startActivity(it) }
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Intent(this, MainNavActivity::class.java).also { startActivity(it) }
                    finish()

                } else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT,
                    ).show()

                }
            }
    }


}