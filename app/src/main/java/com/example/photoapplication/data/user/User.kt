package com.example.photoapplication.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
//    @Ignore
//    var address: Address,
//    @Ignore
//    var company: Company,
    var email: String,
    @PrimaryKey
    var id: Int,
    var name: String,
    var phone: String,
    var username: String,
    var website: String
){
    constructor() : this( "", 0, "", "", "", "")
}