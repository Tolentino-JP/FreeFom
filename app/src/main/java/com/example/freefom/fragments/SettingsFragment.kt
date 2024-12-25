package com.example.freefom.fragments

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

class SettingsFragment : Fragment() {

    private lateinit var view: View
    private lateinit var editProfile: Button
    private lateinit var changePassword: Button
    private lateinit var signOut: Button
    private lateinit var fNameTxt: TextView
    private lateinit var lNameTxt: TextView

    @Nullable
    override fun onCreateView(
        inplater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        view = inplater.inflate(R.layout.fragment_settings_page, container, false)

        editProfile = view.findViewById(R.id.btnEditProfile)
        changePassword = view.findViewById(R.id.btnEditPassword)
        signOut = view.findViewById(R.id.btnSignout)



        editProfile.setOnClickListener {
            (activity as MainNavActivity).replaceFragment(EditProfileFragment(), true)
        }

        changePassword.setOnClickListener{
            (activity as MainNavActivity).replaceFragment(ChangePasswordActivity(), true)
        }

        signOut.setOnClickListener{
            (activity as MainNavActivity).replaceFragment(ChangePasswordActivity(), true)
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve arguments
        val firstName = arguments?.getString("first_name")
        val lastName = arguments?.getString("last_name")

        // Use the data (for example, setting it in a TextView)
        val textView = view.findViewById<TextView>(R.id.text1)
        textView.text = "First Name: $firstName, Last Name: $lastName"
    }
}