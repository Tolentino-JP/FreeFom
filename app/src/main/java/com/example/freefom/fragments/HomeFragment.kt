package com.example.freefom.fragments

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.example.freefom.MainNavActivity
import com.example.freefom.R
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment() {


    private lateinit var view: View
    private lateinit var linearLayoutContainer: LinearLayout
    private lateinit var addBtn: ImageView
    private lateinit var searchBtn: ImageView
    private val db = Firebase.firestore
    private val auth = Firebase.auth


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

        
        addBtn = view.findViewById(R.id.addButton)
        searchBtn = view.findViewById(R.id.searchBtn)
        linearLayoutContainer = view.findViewById(R.id.layoutContainer)

        val editMode = view.findViewById<LinearLayout>(linearLayoutContainer.id)




        addBtn.setOnClickListener{
            val showPopUp = AddAddiction()
            showPopUp.show((activity as MainNavActivity).supportFragmentManager, "showPop")
        }

    }

    override fun onStart() {
        super.onStart()

        val userId = auth.currentUser?.uid
        if (userId != null) {
            fetchDataForUser(userId)
        } else {
            // Handle case when user is not authenticated
            showError("User not logged in.")
        }


    }

    private fun fetchDataForUser(userId: String) {
        db.collection("addiction")
            .document(userId)
            .get()
            .addOnSuccessListener { documents ->

                documents.data?.let { data ->
                    for((key, value) in data){
                        // Linear inner
                        val linearLayout = LinearLayout(requireContext())
                        linearLayout.layoutParams = LinearLayout.LayoutParams(-1, dpToPx(100))
                        linearLayout.orientation = LinearLayout.VERTICAL
                        linearLayout.setPadding(dpToPx(8))


                        linearLayout.setTag(R.id.linear_layout_key, key)

                        // Set up the click listener
                        linearLayout.setOnClickListener { view ->
                            val clickedKey = view.getTag(R.id.linear_layout_key) as String
                            Log.d(TAG, "Clicked key: $clickedKey")

                            // Pass the clickedKey to the EditAddiction fragment
                            val fragment = EditAddiction()
                            val args = Bundle()
                            args.putString("KEY", clickedKey)
                            fragment.arguments = args

                            // Navigate to the fragment
//                            (activity as MainNavActivity).replaceFragment(fragment, true)
                            Toast.makeText(requireContext(), "The key to pass: $clickedKey", Toast.LENGTH_LONG).show()
                        }


                        if (value is Map<*, *>) {
                            Log.d(TAG, "Key: $key, Value is a Map")

                            val time = value["new"]

                            var formattedDate: String = ""
                            if(time is Timestamp){
                                formattedDate = convertTimestampToDate(time)
                            }

                            val addictionName = TextView(requireContext())
                            val addictionCount = TextView(requireContext())

                            addictionName.layoutParams = LinearLayout.LayoutParams(-1, -2)
                            addictionName.text = key
                            addictionName.textSize = 20f
                            addictionName.setPadding(8, 8, 8, 8)

                            addictionCount.layoutParams = LinearLayout.LayoutParams(-1, -2)
                            addictionCount.text = formattedDate
                            addictionCount.textSize = 20f
                            addictionCount.setPadding(8,8,8,8)
                            addictionCount.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_edit,0)

                            linearLayout.addView(addictionName)
                            linearLayout.addView(addictionCount)

                                // Add the TextView to the LinearLayout
                            linearLayoutContainer.addView(linearLayout)

                            // Iterate through the map's entries
//                            for ((innerKey, innerValue) in value) {
//                                Log.d(TAG, "Inner Key: $innerKey, Inner Value: $innerValue")
//                                var formattedDate: String = ""
//                                if(innerValue is Timestamp){
//                                    formattedDate = convertTimestampToDate(innerValue)
//                                }
//
//
//                                // Create a TextView dynamically for each entry in the nested map
//                                val entryTextView = TextView(requireContext())
//                                entryTextView.text = "$key -> $innerKey: $formattedDate"
//                                entryTextView.textSize = 16f
//                                entryTextView.setPadding(8, 8, 8, 8)
//
//                                // Add the TextView to the LinearLayout
//                                linearLayoutContainer.addView(entryTextView)
//                            }
                        } else {
                            Log.d(TAG, "Key: $key, Value: $value")

                            val entryTextView = TextView(requireContext())
                            entryTextView.text = "$key: $value"
                            entryTextView.textSize = 16f
                            entryTextView.setPadding(8, 8, 8, 8)

                            // Add the TextView to the LinearLayout
                            linearLayoutContainer.addView(entryTextView)
                        }


                    }

                }

                // Create a TextView dynamically
//                val userTextView = TextView(requireContext())
//                userTextView.text = documents.data.toString()
//                userTextView.textSize = 16f
//                userTextView.setPadding(8, 8, 8, 8)
//
//                // Add the TextView to the LinearLayout
//                linearLayoutContainer.addView(userTextView)



            }
            .addOnFailureListener { exception ->
                exception.printStackTrace()
            }
    }

    private fun showError(message: String) {
        val errorTextView = TextView(requireContext())
        errorTextView.text = message
        errorTextView.textSize = 16f
        errorTextView.setPadding(8, 8, 8, 8)
        linearLayoutContainer.addView(errorTextView)
    }

    private fun convertTimestampToDate(timestamp: Timestamp): String {
        // Convert Firestore Timestamp to java.util.Date
        val date: Date = timestamp.toDate()

        // Format the date as needed (e.g., "dd-MM-yyyy HH:mm:ss")
        val sdf = SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.getDefault())
        return sdf.format(date)
    }

    private fun dpToPx(dp: Int): Int{
        return (dp * requireContext().resources.displayMetrics.density).toInt()
    }



}