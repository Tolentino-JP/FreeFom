package com.example.freefrom.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.freefrom.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

class AddGoals : DialogFragment() {

    private lateinit var cancel: TextView
    private lateinit var done: TextView
    private lateinit var goalDescription: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_goals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cancel = view.findViewById(R.id.cancelBtn)
        done = view.findViewById(R.id.doneBtn)
        goalDescription = view.findViewById(R.id.goalDescription)

        val name = arguments!!.getString("KEY").toString()



        cancel.setOnClickListener {
            dismiss()
        }

        done.setOnClickListener {

            if(goalDescription.text.isNotEmpty()){

                addGoals(name)
                dismiss()

            }else{
                goalDescription.error = "Enter your goal description"
            }

        }

    }

    override fun onStart() {
        super.onStart()


        val window = dialog?.window
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )


    }


    private fun addGoals(name: String){

        val db = Firebase.firestore
        val userId = Firebase.auth.currentUser!!.uid



        db.collection("addiction")
            .document(userId)
            .update(
                "$name.goals", FieldValue.arrayUnion(goalDescription.text.toString())
            )

        return

    }





}