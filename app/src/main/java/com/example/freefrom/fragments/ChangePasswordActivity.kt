package com.example.freefrom.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.freefrom.R
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.auth

class ChangePasswordActivity : Fragment() {

    private lateinit var backToSettingsBtn: ImageView
    private lateinit var newPassword: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var oldPassword: EditText
    private lateinit var changePasswordBtn: Button

    @Nullable
    override fun onCreateView(
        inplater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        return inplater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backToSettingsBtn = view.findViewById(R.id.backBtn)
        newPassword = view.findViewById(R.id.newPassword)
        confirmPassword = view.findViewById(R.id.confirmPassword)
        oldPassword = view.findViewById(R.id.oldPassword)
        changePasswordBtn = view.findViewById(R.id.changePasswordBtn)

        backToSettingsBtn.setOnClickListener {
            // Navigate back to the previous fragment
            requireActivity().supportFragmentManager.popBackStack()
        }

        changePasswordBtn.setOnClickListener {
            changePassword()
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun changePassword(){

        if(newPassword.text.isNullOrEmpty() || confirmPassword.text.isNullOrEmpty() || oldPassword.text.isNullOrEmpty()){

            newPassword.error = "enter password"
            confirmPassword.error = "enter confirm password"
            oldPassword.error = "enter old password"

        }else{

            if(newPassword.text.length > 6){
                if(newPassword.text.toString() == confirmPassword.text.toString()){

                    val user = Firebase.auth.currentUser

                    if(user != null){

                        val credential = EmailAuthProvider.getCredential(user.email.toString(), oldPassword.text.toString())

                        user.reauthenticate(credential)
                            .addOnCompleteListener { task ->
                                if(task.isSuccessful){
                                    user.updatePassword(newPassword.text.toString())
                                }else{
                                    oldPassword.error = "Wrong password"
                                }
                            }

                    }





                }else{
                    confirmPassword.error = "password is not match"
                }
            }else{
                newPassword.error = "the password must contain 6 characters and above"
            }

        }
    }


}