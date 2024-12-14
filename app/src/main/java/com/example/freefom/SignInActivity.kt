package com.example.freefom

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity



class SignInActivity : AppCompatActivity() {

    private lateinit var loginBtn: Button
    private lateinit var createAccount: TextView
    private lateinit var emailLogin: EditText
    private lateinit var passwordLogin: EditText
    private lateinit var errorText: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_page)


        loginBtn = findViewById(R.id.btnSign)
        errorText = findViewById(R.id.errorTxt)
        emailLogin = findViewById(R.id.emailLogin)
        passwordLogin = findViewById(R.id.passwordLogin)
        createAccount = findViewById(R.id.createAccountBtn)



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

                val dataBaseHelper = DataBaseHelper(this)
                val success = dataBaseHelper.login(userModel)

                var fName = ""
                var lName = ""

                if(success.isNotEmpty()){
                    for(user in success){
                        fName = user.first_name
                        lName = user.last_name
                    }

                    val intent = Intent(this, MainNavActivity::class.java)
                    intent.putExtra("first_name", fName)
                    intent.putExtra("last_name", lName)
                    startActivity(intent)


//                    Toast.makeText(this, success.toString(), Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this, "Error: User not exist", Toast.LENGTH_SHORT).show()
                }







            }

        }// end login


        createAccount.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        }


    }


}




