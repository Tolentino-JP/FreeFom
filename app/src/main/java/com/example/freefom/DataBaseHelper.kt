package com.example.freefom

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context?) : SQLiteOpenHelper(context, "users.db", null, 1) {

    private val tableName = "users"
    private val columnFirstName = "first_name"
    private val columnLastName = "last_name"
    private val columnEmail = "email"
    private val columnPassword = "password"

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement = "CREATE TABLE $tableName (id INTEGER PRIMARY KEY AUTOINCREMENT, $columnFirstName varchar not null, $columnLastName varchar not null, $columnEmail varchar not null, $columnPassword varchar not null);"

        db?.execSQL(createTableStatement)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun signUp(userModel: UserModel): Boolean{

        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(columnFirstName, userModel.first_name)
        cv.put(columnLastName, userModel.last_name)
        cv.put(columnEmail, userModel.email)
        cv.put(columnPassword, userModel.password)

        val result = db.insert(tableName, null, cv)
        return result != -1L

    }


    fun login(userModel: UserModel): List<UserModel>{

        val returnList = mutableListOf<UserModel>()

        val query = "SELECT * FROM $tableName WHERE email = '"+ userModel.email +"' AND password = '"+ userModel.password +"' "

        val db = this.readableDatabase
        val queryResult = db.rawQuery(query, null)


        if(queryResult.moveToFirst()){

            do{
                val userId = queryResult.getInt(0)
                val firstName = queryResult.getString(1)
                val lastName = queryResult.getString(2)
                val email = queryResult.getString(3)
                val password = queryResult.getString(4)

                val user = UserModel(userId, firstName, lastName, email, password)
                returnList.add(user)


            }while (queryResult.moveToNext())

        }else{
            // nothing to do here
        }
        queryResult.close()
        db.close()


        return returnList
    }


}