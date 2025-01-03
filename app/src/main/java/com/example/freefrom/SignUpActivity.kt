package com.example.freefrom

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore


class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var signupBtn: Button
    private lateinit var fName: EditText
    private lateinit var lName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signIn: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)

        auth = Firebase.auth

        signupBtn = findViewById(R.id.signupBtn)
        fName = findViewById(R.id.fnameInput)
        lName = findViewById(R.id.lnameInput)
        email = findViewById(R.id.emailInput)
        password = findViewById(R.id.passwrodInput)
        signIn = findViewById(R.id.signInTxt)

        signupBtn.setOnClickListener{
            signUp(email.text.toString(), password.text.toString(), fName.text.toString(), lName.text.toString())
        }
        signIn.setOnClickListener{
            Intent(this, SignInActivity::class.java).also { startActivity(it) }
        }
    }

    private fun signUp(email: String, password: String, fName: String, lName: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        val db = Firebase.firestore
                        val users = hashMapOf(
                            "first name" to fName,
                            "last name" to lName
                        )

                        db.collection("users").document(userId).set(users)
                            .addOnSuccessListener {
                                Toast.makeText(baseContext, "User registered successfully.", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error writing document", e)
                            }
                        Intent(this, SignInActivity::class.java).also { startActivity(it) }

                    } else {
                        Log.w(TAG, "User ID is null after successful authentication.")
                    }
                } else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }// end signUp
}