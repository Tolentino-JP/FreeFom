package com.example.freefom.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.freefom.MainNavActivity
import com.example.freefom.R
import com.example.freefom.SignInActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class SettingsFragment : Fragment() {

    private lateinit var view: View
    private lateinit var editProfile: Button
    private lateinit var changePassword: Button
    private lateinit var signOut: Button
    private lateinit var fullName: TextView
    private lateinit var userId: String
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore

    @Nullable
    override fun onCreateView(
        inplater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        view = inplater.inflate(R.layout.fragment_settings_page, container, false)
        userId = auth.currentUser!!.uid

        fetchData(userId)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editProfile = view.findViewById(R.id.btnEditProfile)
        changePassword = view.findViewById(R.id.btnEditPassword)
        signOut = view.findViewById(R.id.btnSignout)

        val context = activity as MainNavActivity

        editProfile.setOnClickListener {
            context.replaceFragment(EditProfileFragment(), true)
        }

        changePassword.setOnClickListener{
            context.replaceFragment(ChangePasswordActivity(), true)
        }

        signOut.setOnClickListener{
            Firebase.auth.signOut()
            Intent(context, SignInActivity::class.java).also { startActivity(it) }
        }

    }

    private fun fullName(fName: String, lName: String): String{
        return "$fName $lName"
    }

    private fun fetchData(userId: String){
        db.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener {
                fullName = view.findViewById(R.id.fullName)

                val fName = it.getString("first name").toString()
                val lName = it.getString("last name").toString()

                fullName.text = fullName(fName,lName)
            }
    }
}