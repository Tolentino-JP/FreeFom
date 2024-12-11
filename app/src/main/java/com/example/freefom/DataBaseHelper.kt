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
        val createTableStatement = "CREATE TABLE $tableName (id INTEGER primary AUTOINCREMENT, $columnFirstName varchar not null, $columnLastName varchar not null, $columnEmail varchar not null, $columnPassword varchar not null);"

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


}