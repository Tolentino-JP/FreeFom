package com.example.freefom

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public class DataBaseHelper(context: Context?) : SQLiteOpenHelper(context, "users.db", null, 1) {

    val tableName = "users"
    val columnFirstName = "first_name"
    val columnLastName = "last_name"
    val columnEmail = "email"
    val columnPassword = "password"

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement = "CREATE TABLE $tableName (id INTEGER PRIMARY KEY AUTOINCREMENT, $columnFirstName varchar not null, $columnLastName varchar not null, $columnEmail varchar not null, $columnPassword varchar not null);"

        db?.execSQL(createTableStatement)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun SignUp(userModel: UserModel): Boolean{

        var db = this.writableDatabase
        val cv = ContentValues()

        cv.put(columnFirstName, userModel.first_name)
        cv.put(columnLastName, userModel.last_name)
        cv.put(columnEmail, userModel.email)
        cv.put(columnPassword, userModel.password)

        val result = db.insert(tableName, null, cv)
        if(result == -1L){
            return false
        }else{
            return true
        }

    }


    fun Login(userModel: UserModel): List<UserModel>{

        val returnList = mutableListOf<UserModel>()

        val query = "SELECT * FROM $tableName WHERE email = '"+ userModel.email +"' AND password = '"+ userModel.password +"' "

        var db = this.readableDatabase
        var queryResult = db.rawQuery(query, null)


        if(queryResult.moveToFirst()){

            do{
                var userId = queryResult.getInt(0)
                var first_name = queryResult.getString(1)
                var last_name = queryResult.getString(2)
                var email = queryResult.getString(3)
                var password = queryResult.getString(4)

                var userModel = UserModel(userId, first_name, last_name, email, password)
                returnList.add(userModel)


            }while (queryResult.moveToNext())

        }else{
            // nothing to do here
        }
        queryResult.close()
        db.close()


        return returnList
    }


}