package com.example.freefom.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.freefom.MainNavActivity
import com.example.freefom.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class EditProfileFragment : Fragment() {

    private lateinit var view: View
    private lateinit var userId: String
    private lateinit var backToSettingsBtn: Button
    private lateinit var fNameProfile: EditText
    private lateinit var lNameProfile: EditText
    private lateinit var emailProfile: EditText
    private lateinit var cancelBtn: Button
    private lateinit var saveBtn: Button
    private val db = Firebase.firestore
    private val auth = Firebase.auth


    @Nullable
    override fun onCreateView(
        inplater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        view = inplater.inflate(R.layout.fragment_edit_profile_page, container, false)

        userId = auth.currentUser!!.uid

        fetchData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saveBtn = view.findViewById(R.id.saveBtn)
        cancelBtn = view.findViewById(R.id.cancelBtn)


        cancelBtn.setOnClickListener{
            alertDialog()
//            (activity as MainNavActivity).replaceFragment(SettingsFragment(), true)
        }

        saveBtn.setOnClickListener{



        }

    }

    private fun fetchData(){
        db.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener {
                fNameProfile = view.findViewById(R.id.fNameProfile)
                lNameProfile = view.findViewById(R.id.lNameProfile)
                emailProfile = view.findViewById(R.id.emailProfile)

                val fName = it.getString("first name").toString()
                val lName = it.getString("last name").toString()
                val email = auth.currentUser!!.email.toString()

                fNameProfile.setText(fName)
                lNameProfile.setText(lName)
                emailProfile.setText(email)


            }
    }




    private fun alertDialog(){

        val builder = AlertDialog.Builder(activity as MainNavActivity)

        builder.setTitle("You want to cancel?")
            .setMessage("Are you sure to cancel? all changes will not be saved")
            .setCancelable(true)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                (activity as MainNavActivity).replaceFragment(SettingsFragment(), true)
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
            .show()
    }



}