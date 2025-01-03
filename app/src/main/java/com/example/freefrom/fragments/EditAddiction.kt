package com.example.freefrom.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.freefrom.MainNavActivity
import com.example.freefrom.R
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class EditAddiction : Fragment() {

    private lateinit var addictionName: TextView
    private lateinit var addGoalsTxt: TextView
    private lateinit var addGoalsList: ListView
    private lateinit var pastStreak: TextView
    private lateinit var daysContainer: TextView
    private lateinit var hoursContainer: TextView
    private lateinit var minutesContainer: TextView
    private lateinit var startDateTxt: TextView
    private lateinit var resetBtn: Button
    private lateinit var backBtn: ImageView

    private var handler: Handler? = null
    private var runnable: Runnable? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_addiction, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addGoalsTxt = view.findViewById(R.id.addGoalsBtn)
        addictionName = view.findViewById(R.id.addictionName)
        addGoalsList = view.findViewById(R.id.addGoalsContainer)
        daysContainer = view.findViewById(R.id.daysContainer)
        hoursContainer = view.findViewById(R.id.hoursContainer)
        minutesContainer = view.findViewById(R.id.minutesContainer)
        startDateTxt = view.findViewById(R.id.startDateTxt)
        pastStreak = view.findViewById(R.id.pastStreaks)
        resetBtn = view.findViewById(R.id.resetBtn)
        backBtn = view.findViewById(R.id.backBtn)

        val name = arguments?.getString("KEY").toString()
        val userId = Firebase.auth.currentUser!!.uid
        val db = Firebase.firestore

        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            // Call a method to refresh the fragment's content
            fetchData(name, userId, db)
            // Repeat the runnable every 1000 milliseconds (1 second)
            handler?.postDelayed(runnable!!, 1000)
        }

        // Start the runnable
        handler?.post(runnable!!)


        addictionName.text = name

        addGoalsTxt.setOnClickListener {
            val fragment = AddGoals()

            val args = Bundle()

            args.putString("KEY", name)

            fragment.arguments = args
            fragment.show((activity as MainNavActivity).supportFragmentManager, "showPop")
        }

        resetBtn.setOnClickListener {
            resetTime(name, userId, db)
        }

        backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Remove any pending posts from the handler
        handler?.removeCallbacks(runnable!!)
        // Clear the handler and runnable references
        handler = null
        runnable = null
    }

    private fun fetchData(name:String, userId: String, db: FirebaseFirestore){

        db.collection("addiction")
            .document(userId)
            .get()
            .addOnSuccessListener { documents ->

                val datas = documents.data!!.toMutableMap()

                for((key,data) in datas){

                    if(key == name){
                        if(data is Map<*,*>){

                            for((_, value) in data) {

                                if(value is ArrayList<*>){
                                    val arrayAdapter = ArrayAdapter(requireContext(),  R.layout.list_view_layout, R.id.textView1, value)
                                    addGoalsList.adapter = arrayAdapter
                                }
                            }

                            // compute the latest Streak
                            val newTime = data["new"]
                            val oldTime = data["old"]

                            if(newTime is Timestamp && oldTime is Timestamp){
                                startDateTxt.text =  convertTimestampToDate(newTime)
                                dateTimeSample(oldTime, newTime)
                            }

                        }
                    }

                }

            }

    }

    private fun dateTimeSample(oldTime: Timestamp, newTime: Timestamp) {
        val date1: Date = oldTime.toDate() // Convert Timestamp to Date
        val date2: Date = newTime.toDate() // Convert Timestamp to Date
        val currentDate = Date() // Get current date and time

        // Calculate the difference in milliseconds



        var differenceInMillis = currentDate.time - date2.time

        var days = TimeUnit.MILLISECONDS.toDays(differenceInMillis)
        var hours = TimeUnit.MILLISECONDS.toHours(differenceInMillis) % 24
        var minutes = TimeUnit.MILLISECONDS.toMinutes(differenceInMillis) % 60

        daysContainer.text = days.toString()
        hoursContainer.text = hours.toString()
        minutesContainer.text = minutes.toString()

        if(date1 == date2){
            pastStreak.text = "$days days $hours hours $minutes minutes"
        }else{
            // for best streak
            differenceInMillis = date2.time - date1.time

            days = TimeUnit.MILLISECONDS.toDays(differenceInMillis)
            hours = TimeUnit.MILLISECONDS.toHours(differenceInMillis) % 24
            minutes = TimeUnit.MILLISECONDS.toMinutes(differenceInMillis) % 60

            pastStreak.text = "$days days $hours hours $minutes minutes"
        }


    }

    private fun convertTimestampToDate(timestamp: Timestamp): String {
        // Convert Firestore Timestamp to java.util.Date
        val date: Date = timestamp.toDate()

        // Format the date as needed (e.g., "MM-dd-yyyy HH:mm:ss")
        val sdf = SimpleDateFormat("MMM-dd-yyyy HH:mm:ss", Locale.getDefault())
        return sdf.format(date)
    }

    private fun resetTime(name: String, userId: String, db: FirebaseFirestore){

        db.collection("addiction")
            .document(userId)
            .get()
            .addOnSuccessListener { documents ->

                val document = documents.data!!.toMutableMap()

                for((key,value) in document){

                    if(key == name){
                        if(value is Map<*,*>){

                            var old = value["old"]
                            var new = value["new"]
                            val currentDate = Date()

                            if(old is Timestamp && new is Timestamp){
                                old = currentDate.time - old.toDate().time
                                new = currentDate.time - new.toDate().time

                                val currentTimeStamp = Timestamp(currentDate)

                                if(new > old){
                                    
                                    db.collection("addiction")
                                        .document(userId)
                                        .update(
                                            "$name.old", currentTimeStamp,
                                            "$name.new", currentTimeStamp
                                        )
                                }else{

                                    db.collection("addiction")
                                        .document(userId)
                                        .update(
                                            "$name.new", currentTimeStamp
                                        )

                                }

                            }





                        }
                    }

                }

            }

    }


}