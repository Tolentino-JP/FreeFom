package com.example.freefom.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.freefom.MainNavActivity
import com.example.freefom.R

class EditAddiction : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_addiction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addGoals = view.findViewById<TextView>(R.id.addGoalsBtn)

        addGoals.setOnClickListener {

            (activity as MainNavActivity).replaceFragment(EditProfileFragment(), true)

        }

    }


}