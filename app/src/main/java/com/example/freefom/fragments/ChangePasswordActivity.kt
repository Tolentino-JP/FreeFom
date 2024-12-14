package com.example.freefom.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.freefom.R

class ChangePasswordActivity : Fragment() {

    private lateinit var view: View

    @Nullable
    override fun onCreateView(
        inplater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        view = inplater.inflate(R.layout.fragment_change_password, container, false)

        val backToSettingsBtn = view.findViewById<TextView>(R.id.backToSettingsBtn)

        backToSettingsBtn.setOnClickListener {
            // Navigate back to the previous fragment
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }
}