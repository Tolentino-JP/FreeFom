package com.example.freefom.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.freefom.R

class HomeFragment : Fragment() {

    private lateinit var view: View

    @Nullable
    override fun onCreateView(
        inplater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        view = inplater.inflate(R.layout.fragment_home_page, container, false)

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