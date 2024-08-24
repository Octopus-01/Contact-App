package com.learn.contactsapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contect_table")
data class Contact(
 @PrimaryKey(autoGenerate = true) var id: Int=0,
 @ColumnInfo(name = "user_name") var name: String,
 var number: String,
 var email: String,
 var dateofCreation: Long,
 var isactive: Boolean,
 var image : ByteArray ?= null
) {

}