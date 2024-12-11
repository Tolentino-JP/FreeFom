package com.example.freefom

public class UserModel{

    var id: Int = 0
    var first_name: String = ""
    var last_name: String = ""
    var email: String = ""
    var password: String = ""

    //to string
    override fun toString(): String {
        return "UserModel(id=$id, " +
                "first_name='$first_name', " +
                "last_name='$last_name', " +
                "email='$email', " +
                "password='$password')"
    }


    //constructor
    constructor(id: Int, first_name: String, last_name: String, email: String, password: String) {
        this.id = id
        this.first_name = first_name
        this.last_name = last_name
        this.email = email
        this.password = password
    }



    constructor(){

    }

    constructor(email: String, password: String) {
        this.email = email
        this.password = password
    }

//    public fun getId(): Int{
//        return id
//    }
//
//    public fun getFname(): String{
//        return first_name
//    }
//
//    public fun getLname():String{
//        return last_name
//    }
//
//    public fun getEmail():String{
//        return email
//    }
//
//    public fun getPassword():String{
//        return password
//    }









}