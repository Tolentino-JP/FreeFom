package com.example.freefrom.fragments

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
import com.example.freefrom.MainNavActivity
import com.example.freefrom.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class EditProfileFragment : Fragment() {

    private lateinit var userId: String
    private lateinit var fNameProfile: EditText
    private lateinit var lNameProfile: EditText
    private lateinit var cancelBtn: Button
    private lateinit var saveBtn: Button


    @Nullable
    override fun onCreateView(
        inplater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        return inplater.inflate(R.layout.fragment_edit_profile_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore
        val auth = Firebase.auth

        fNameProfile = view.findViewById(R.id.fNameProfile)
        lNameProfile = view.findViewById(R.id.lNameProfile)

        saveBtn = view.findViewById(R.id.saveBtn)
        cancelBtn = view.findViewById(R.id.cancelBtn)
        userId = auth.currentUser!!.uid

        fetchData(userId, db)

        cancelBtn.setOnClickListener{
            alertDialog()
        }

        saveBtn.setOnClickListener{

            updateData(userId, db)

        }

    }

    override fun onResume() {
        super.onResume()


    }

    private fun updateData(userId: String, db: FirebaseFirestore) {

        val newUserInfo = hashMapOf(
            "first name" to fNameProfile.text.toString(),
            "last name" to lNameProfile.text.toString()
        )

        db.collection("users")
            .document(userId)
            .set(newUserInfo)

    }

    private fun fetchData(userId:String, db: FirebaseFirestore){
        db.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener {


                val fName = it.getString("first name").toString()
                val lName = it.getString("last name").toString()

                fNameProfile.setText(fName)
                lNameProfile.setText(lName)
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