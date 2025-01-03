package com.example.freefrom.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.freefrom.R
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone


class AddAddiction : DialogFragment() {

    private lateinit var cancelBtn: TextView
    private lateinit var doneBtn: TextView
    private lateinit var addictionName: EditText
    private lateinit var time: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_addiction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cancelBtn = view.findViewById(R.id.btnCancel)
        doneBtn = view.findViewById(R.id.btnDone)
        addictionName = view.findViewById(R.id.inputAddiction)
        time = view.findViewById(R.id.inputStartDate)

        time.setOnClickListener{
            dateTimePicker()
        }

        cancelBtn.setOnClickListener{
            dismiss()
        }

        doneBtn.setOnClickListener {

            if(addictionName.text.isNotEmpty() && time.text.isNotEmpty()){
                val timestamp = convertToTimestamp(time.text.toString())

                if(timestamp != null){
                    addData(addictionName.text.toString(), timestamp)
                    dismiss()
                }

            }

        }


    }

    override fun onStart() {
        super.onStart()

        // Set the dialog to take up the full width and height of the screen
        val window = dialog?.window
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun addData(addictionName: String, startDate: Timestamp){

        val db = Firebase.firestore
        val userId = Firebase.auth.currentUser!!.uid

        val addictCategory = hashMapOf(
            addictionName to mapOf(
                "new" to startDate,
                "old" to startDate
            )
        )

        db.collection("addiction")
            .document(userId)
            .set(addictCategory, SetOptions.merge())
            .addOnSuccessListener {
                Toast.makeText(
                    requireContext(),
                    "User registered successfully.",
                    Toast.LENGTH_SHORT
                ).show()
            }

    }

    private fun convertToTimestamp(dateString: String): Timestamp? {
        return try {
            // Define the expected date format
            val format = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault())
            val date = format.parse(dateString) // Parse the string into a Date
            Timestamp(date!!) // Convert Date to Firebase Timestamp
        } catch (e: Exception) {
            e.printStackTrace()
            null // Return null if parsing fails
        }
    }

    private fun dateTimePicker(){

        val calendar = Calendar.getInstance()

        DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->

            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)


            TimePickerDialog(
                requireContext(),
                { _, hourOfDay, minute ->

                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)

                    val format = "MM-dd-yyyy HH:mm:ss"
                    val dateFormat = SimpleDateFormat(format, Locale.US)

                    dateFormat.timeZone = TimeZone.getDefault()

                    time.setText(dateFormat.format(calendar.time))
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()

    }



}