package com.example.freefom

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {

    lateinit var loginBtn: Button
    lateinit var createAccount: TextView
    lateinit var emailLogin: EditText
    lateinit var passwordLogin: EditText
//    lateinit var passwordInput: EditText
//    lateinit var emailInput:  EditText
    lateinit var errorText: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.login_page)


        loginBtn = findViewById(R.id.btnSign)
        errorText = findViewById(R.id.errorTxt)
        emailLogin = findViewById(R.id.emailLogin)
        passwordLogin = findViewById(R.id.passwordLogin)
        createAccount = findViewById(R.id.createAccountBtn)
//        passwordInput = findViewById(R.id.passwrodInput)
//        emailInput = findViewById(R.id.emailInput)



        loginBtn.setOnClickListener{

            if(emailLogin.text.toString().isEmpty() || passwordLogin.text.toString().isEmpty()){
                errorText.visibility = TextView.VISIBLE
            }else{

                var userModel = UserModel()

                try {
                    userModel = UserModel(emailLogin.text.toString(), passwordLogin.text.toString())

                }catch (e: Exception){
                    Toast.makeText(this, "Error login",Toast.LENGTH_SHORT).show()
                }

                var dataBaseHelper = DataBaseHelper(this)
                //val success = dataBaseHelper.Login(userModel)
            }




        }// end login


        createAccount.setOnClickListener( View.OnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        })







    }


}




