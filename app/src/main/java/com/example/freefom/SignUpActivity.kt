package com.example.freefom

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class SignUpActivity : AppCompatActivity() {

    private lateinit var signupBtn: Button
    private lateinit var fName: EditText
    private lateinit var lName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signIn: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.signup_page)


        signupBtn = findViewById(R.id.signupBtn)
        fName = findViewById(R.id.fnameInput)
        lName = findViewById(R.id.lnameInput)
        email = findViewById(R.id.emailInput)
        password = findViewById(R.id.passwrodInput)
        signIn = findViewById(R.id.signInTxt)

        val intent = Intent(this, SignInActivity::class.java)


        signupBtn.setOnClickListener{
            
            if(fName.text.toString().isEmpty() || lName.text.toString().isEmpty() || email.text.toString().isEmpty() || password.text.toString().isEmpty()){
                Toast.makeText(this, "Error: Fill all the empty box", Toast.LENGTH_SHORT).show()
            }else{

                var userModel = UserModel()

                try{
                    userModel = UserModel(-1, fName.text.toString(), lName.text.toString(), email.text.toString(), password.text.toString())
                }catch(e: Exception){
                    Toast.makeText(this, "Error login",Toast.LENGTH_SHORT).show()
                }

                val dataBaseHelper = DataBaseHelper(this)
                val success = dataBaseHelper.signUp(userModel)

                if(success){
                    startActivity(intent)
//                    Toast.makeText(this, "Success Created", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error: SignUp Failed", Toast.LENGTH_SHORT).show()
                }

            }
            
        }// end signup

        signIn.setOnClickListener{
            startActivity(intent)
        }// end




    }
}